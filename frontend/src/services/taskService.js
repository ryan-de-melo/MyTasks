import api from "./api";

export function createTask(task) {
    return api.post("/api/tasks", task);
}

export function listTasks() {
    return api.get("/api/tasks");
}