from typing import List
from fastapi import FastAPI
from pydantic import BaseModel
import pulp

app = FastAPI()


class Student(BaseModel):
    id: int
    name: str
    topic: str  # Área temática — debe coincidir con specialty del tutor
    academicRisk: int  # Escala 1–5
    priority: int  # Escala 1–10


class Tutor(BaseModel):
    id: int
    name: str
    specialty: str  # Especialidad — debe coincidir con topic del estudiante
    maxCapacity: int  # Máximo de estudiantes que puede atender


class OptimizationRequest(BaseModel):
    students: List[Student]
    tutors: List[Tutor]


@app.post("/optimize")
def optimize(request: OptimizationRequest):
    students = request.students
    tutors = request.tutors

    # Crear el problema de maximización
    problem = pulp.LpProblem("TutorAssignment", pulp.LpMaximize)

    # Variables de decisión binarias x[student_id, tutor_id]
    x = {}
    for student in students:
        for tutor in tutors:
            name = f"x_{student.id}_{tutor.id}"
            x[(student.id, tutor.id)] = pulp.LpVariable(name, cat="Binary")

    # 4.3 Función Objetivo
    # Maximizar suma de (prioridad × riesgo) solo para pares compatibles
    problem += pulp.lpSum(
        student.priority * student.academicRisk * x[(student.id, tutor.id)]
        for student in students
        for tutor in tutors
        if student.topic.lower() == tutor.specialty.lower()  # compatibilidad temática
    )

    # 4.4 Restricciones del Modelo
    # R1: Cada estudiante asignado a máximo un tutor
    for student in students:
        problem += (
            pulp.lpSum(x[(student.id, tutor.id)] for tutor in tutors) <= 1
        )

    # R2: Cada tutor no supera su capacidad máxima
    for tutor in tutors:
        problem += (
            pulp.lpSum(x[(student.id, tutor.id)] for student in students)
            <= tutor.maxCapacity
        )

    # R3: Forzar cero en asignaciones sin compatibilidad temática
    for student in students:
        for tutor in tutors:
            if student.topic.lower() != tutor.specialty.lower():
                problem += x[(student.id, tutor.id)] == 0

    # 4.5 Resolución y Respuesta
    # Resolver con el solver CBC (COIN-OR Branch and Cut)
    problem.solve(pulp.PULP_CBC_CMD(msg=False))

    # Construir lista de asignaciones donde x[i,j] = 1
    assignments = []
    for student in students:
        for tutor in tutors:
            if pulp.value(x[(student.id, tutor.id)]) == 1:
                assignments.append(
                    {
                        "studentId": student.id,
                        "studentName": student.name,
                        "tutorId": tutor.id,
                        "tutorName": tutor.name,
                        "topic": student.topic,
                        "score": student.priority * student.academicRisk,
                    }
                )

    return {
        "status": pulp.LpStatus[problem.status],  # 'Optimal', 'Infeasible', etc.
        "objectiveValue": pulp.value(problem.objective),  # Valor total maximizado
        "assignments": assignments,  # Lista de asignaciones
    }
