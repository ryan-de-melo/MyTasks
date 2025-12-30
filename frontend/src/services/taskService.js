import { api } from "./api";

export function createTask(task) {
    return api.post("/tasks", task);
}