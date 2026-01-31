import { useState, useEffect } from "react";
import { listTasks } from "../services/taskService";

function ListTaskPage() {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(true);

  useEffect(() => {
    fetchTasks();
  }, []);

  async function fetchTasks() {
    try {
      const response = await listTasks();
      setTasks(response.data);
    } catch (e) {
      console.error(e);
      setError("Error while loading tasks");
    } finally {
      setLoading(false);
    }
  }

  function getPriorityStyle(priority) {
    let style = null;
    switch (priority) {
        case "HIGH":
            style = "text-red-400 bg-red-400/10 border-red-400/20";
            break;
        case "MEDIUM":
            style = "text-yellow-400 bg-yellow-400/10 border-yellow-400/20";
            break;
        case "LOW":
            style = "text-green-400 bg-green-400/10 border-green-400/20";
            break;
        default:
            style = "text-zinc-400 bg-zinc-400/10 border-zinc-400/20";
    }

    return style;
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-900 px-4 py-12">
      <div className="w-full max-w-md bg-zinc-800 rounded-2xl shadow-lg p-6">
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-2xl font-semibold text-zinc-100">My Tasks</h2>
          <span className="text-sm text-zinc-400 bg-zinc-900 px-3 py-1 rounded-full border border-zinc-700">
            Total: {tasks.length}
          </span>
        </div>

        {!loading && !error && tasks.length === 0 && (
            <div className="text-center text-zinc-500 py-10 border border-dashed border-zinc-700 rounded-lg">
                Nenhuma tarefa encontrada
            </div>
        )}



      </div>
    </div>
  );
}

export default ListTaskPage;
