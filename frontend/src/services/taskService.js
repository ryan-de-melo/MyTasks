import api from "./api";

export function createTask(task) {
    return api.post("/api/tasks", task);
}

export function listTasks() {
    return api.get("/api/tasks");
}

export function editTask(task) {
    return api.put(`/api/tasks/${task.id}`, {
      title: task.title,
      description: task.description,
      priority: task.priority,
      status: task.status,
    });
}

export function deleteTask(task) {
    return api.delete(`/api/tasks/${task.id}`);
}