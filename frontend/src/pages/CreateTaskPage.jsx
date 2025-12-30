import { useState } from "react";
import { createTask } from "../services/taskService";

function CreateTaskPage() {
  const [form, setForm] = useState({
    title: "",
    description: "",
    priority: "LOW",
    status: "DO",
    userId: 1,
    deadline: "",
  });

  function handleChange(event) {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      await createTask({
        ...form,
        deadline: form.deadline ? new Date(form.deadline).toISOString() : null,
      });

      alert("Success");
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <>
      <h2>Criar Task</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="title"
          placeholder="Título"
          value={form.title}
          onChange={handleChange}
        />

        <textarea
          name="description"
          placeholder="Descrição"
          value={form.description}
          onChange={handleChange}
        />

        <select name="priority" value={form.priority} onChange={handleChange}>
          <option value="HIGH">Alta</option>
          <option value="MEDIUM">Média</option>
          <option value="LOW">Baixa</option>
        </select>

        <select name="status" value={form.status} onChange={handleChange}>
          <option value="DO">A Fazer</option>
          <option value="DOING">Fazendo</option>
          <option value="DONE">Concluída</option>
        </select>

        <input
          type="datetime-local"
          name="deadline"
          value={form.deadline}
          onChange={handleChange}
        />

        <button type="submit">Criar</button>
      </form>
    </>
  );
}

export default CreateTaskPage;
