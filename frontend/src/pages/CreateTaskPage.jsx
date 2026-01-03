import { useState } from "react";
import { createTask } from "../services/taskService";

function CreateTaskPage() {
  const [form, setForm] = useState({
    title: "",
    description: "",
    priority: "LOW",
    status: "DO",
    userId: 1,
    deadlineDate: "",
    deadlineTime: null,
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
    <div className="min-h-screen flex items-center justify-center bg-zinc-900 px-4">
      <div className="w-full max-w-md bg-zinc-700 rounded-2xl shadow-lg p-6">
        <h2 className="text-2xl max-w-md font-semibold text-zinc-100 mb-6">
          Criar Task
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            name="title"
            placeholder="Título"
            value={form.title}
            onChange={handleChange}
            className="w-full rounded-lg bg-zinc-900 border border-zinc-700 px-3 py-2 text-zinc-100 placeholder-zinc-400 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />

          <textarea
            name="description"
            placeholder="Descrição"
            value={form.description}
            onChange={handleChange}
            className="w-full rounded-lg bg-zinc-900 border border-zinc-700 px-3 py-2 text-zinc-100 placeholder-zinc-400 resize-none focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />

          <div className="grid grid-cols-2 gap-4">
            <select
              name="priority"
              value={form.priority}
              onChange={handleChange}
              className="w-full rounded-lg bg-zinc-900 border border-zinc-700 px-3 py-2 text-zinc-100 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            >
              <option value="HIGH">Alta</option>
              <option value="MEDIUM">Média</option>
              <option value="LOW">Baixa</option>
            </select>

            <select
              name="status"
              value={form.status}
              onChange={handleChange}
              className="w-full rounded-lg bg-zinc-900 border border-zinc-700 px-3 py-2 text-zinc-100 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            >
              <option value="DO">A Fazer</option>
              <option value="DOING">Fazendo</option>
              <option value="DONE">Concluída</option>
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <input
              type="date"
              name="deadlineDate"
              value={form.deadlineDate}
              onChange={handleChange}
              className="w-full rounded-lg bg-zinc-900 border border-zinc-700 px-3 py-2 text-zinc-100 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />

            <input
              type="time"
              name="deadlineTime"
              value={form.deadlineTime}
              onChange={handleChange}
              className="w-full rounded-lg bg-zinc-900 border border-zinc-700 px-3 py-2 text-zinc-100 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          <button
            type="submit"
            className="w-full mt-2 rounded-lg bg-zinc-900 py-2 font-medium text-white hover:bg-indigo-500 transition-colors"
          >
            Criar
          </button>
        </form>
      </div>
    </div>
  );
}

export default CreateTaskPage;
