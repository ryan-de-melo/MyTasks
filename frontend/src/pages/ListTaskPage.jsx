import { useState, useEffect } from "react";
import { listTasks, editTask } from "../services/taskService";
import { CheckCircle2, Clock, CircleDashed, Calendar } from "lucide-react";

function ListTaskPage() {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(true);

  const [editingTitleId, setEditingTitleId] = useState(null);
  const [editingDescriptionId, setEditingDescriptionId] = useState(null);

  const [titleDraft, setTitleDraft] = useState("");
  const [descriptionDraft, setDescriptionDraft] = useState("");

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
        style = "text-yellow-500 bg-orange-400/10 border-orange-400/20";
        break;
      case "LOW":
        style = "text-green-400 bg-green-400/10 border-green-400/20";
        break;
      default:
        style = "text-zinc-400 bg-zinc-400/10 border-zinc-400/20";
    }

    return style;
  }

  function getPriorityLabel(priority) {
    const labels = { HIGH: "Alta", MEDIUM: "Média", LOW: "Baixa" };
    return labels[priority] || priority;
  }

  function getStatusIcon(status) {
    let icon = null;

    switch (status) {
      case "DONE":
        icon = <CheckCircle2 size={16} className="text-emerald-500" />;
        break;
      case "DOING":
        icon = <Clock size={16} className="text-indigo-500" />;
        break;
      default:
        icon = <CircleDashed size={16} className="text-zinc-500" />;
    }

    return icon;
  }

  function formatDate(dateString) {
    let formated = null;
    if (dateString) {
      formated = new Date(dateString).toLocaleDateString("pt-BR", {
        day: "2-digit",
        month: "long",
        hour: "2-digit",
        minute: "2-digit",
      });
    }
    return formated;
  }

  function getStatusText(status) {
    let text = null;

    if (status === "DO") {
      text = "A Fazer";
    } else if (status === "DOING") {
      text = "Fazendo";
    } else {
      text = "Concluída";
    }

    return text;
  }

  function getNextStatus(status) {
    let nextStatus = null;

    switch (status) {
      case "DONE":
        nextStatus = "DO";
        break;
      case "DO":
        nextStatus = "DOING";
        break;
      default:
        nextStatus = "DONE";
    }

    return nextStatus;
  }

  async function handleToggleStatus(task) {
    const updatedTask = {
      ...task,
      status: getNextStatus(task.status),
    };

    try {
      await editTask(updatedTask);

      setTasks((prev) => prev.map((t) => (t.id === task.id ? updatedTask : t)));
    } catch (error) {
      console.error("Error while updating task", error);
    }
  }

  function getNextPriority(priority) {
    let nextPriority = null;
    switch (priority) {
      case "LOW":
        nextPriority = "MEDIUM";
        break;
      case "MEDIUM":
        nextPriority = "HIGH";
        break;
      case "HIGH":
        nextPriority = "LOW";
        break;
      default:
        nextPriority = priority;
    }
    return nextPriority;
  }

  async function handleTogglePriority(task) {
    const updatedTask = {
      ...task,
      priority: getNextPriority(task.priority),
    };

    try {
      await editTask(updatedTask);

      setTasks((prev) => prev.map((t) => (t.id === task.id ? updatedTask : t)));
    } catch (error) {
      console.error("Error while updating task", error);
    }
  }

  function startEditingTitle(task) {
    setEditingTitleId(task.id);
    setTitleDraft(task.title ?? "");
  }

  function startEditingDescription(task) {
    setEditingDescriptionId(task.id);
    setDescriptionDraft(task.description ?? "");
  }

  function cancelTitleEditing() {
    setEditingTitleId(null);
    setTitleDraft("");
  }

  function cancelEditingDescription() {
    setEditingDescriptionId(null);
    setDescriptionDraft("");
  }

  async function saveDescription(task) {
    const updatedTask = {
      id: task.id,
      title: task.title,
      description: descriptionDraft,
      priority: task.priority,
      status: task.status,
    };

    try {
      await editTask(updatedTask);

      setTasks((prev) =>
        prev.map((t) =>
          t.id === task.id ? { ...t, description: descriptionDraft } : t,
        ),
      );

      setEditingDescriptionId(null);
      setDescriptionDraft("");
    } catch (error) {
      console.error("Error while updating task", error);
    }
  }

  async function saveTitle(task) {
    const updatedTask = {
      ...task,
      title: titleDraft,
    };

    try {
      await editTask(updatedTask);

      setTasks((prev) => prev.map((t) => (t.id === task.id ? updatedTask : t)));

      setEditingTitleId(null);
    } catch (error) {
      console.error("Error while updating title", error);
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-900 px-4 py-12">
      <div className="w-full max-w-md bg-zinc-800 rounded-2xl shadow-lg p-6">
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-2xl font-semibold text-zinc-100">MyTasks</h2>
          <span className="text-sm text-zinc-400 bg-zinc-900 px-3 py-1 rounded-full border border-zinc-700">
            Total: {tasks.length}
          </span>
        </div>

        {!loading && !error && tasks.length === 0 && (
          <div className="text-center text-zinc-500 py-10 border border-dashed border-zinc-700 rounded-lg">
            Nenhuma tarefa encontrada
          </div>
        )}

        <div className="space-y-3">
          {tasks.map((task) => (
            <div
              key={task.id}
              className="group bg-zinc-900 border border-zinc-800 rounded-xl p-4 transition-all hover:border-indigo-600 hover:shadow-md"
            >
              <div className="flex items-start justify-between gap-4">
                <div className="space-y-1 flex-1">
                  <div className="flex items-center gap-2">
                    {editingTitleId === task.id ? (
                      <input
                        value={titleDraft}
                        onChange={(e) => setTitleDraft(e.target.value)}
                        onBlur={() => saveTitle(task)}
                        onKeyDown={(e) => {
                          if (e.key === "Enter") saveTitle(task);
                          if (e.key === "Escape") cancelTitleEditing();
                        }}
                        autoFocus
                        className="bg-zinc-900 text-zinc-100 text-lg font-medium rounded px-1 outline-none"
                      />
                    ) : (
                      <h3
                        onClick={() => startEditingTitle(task)}
                        className="font-medium text-zinc-100 text-lg cursor-pointer"
                      >
                        {task.title}
                      </h3>
                    )}
                    <button
                      onClick={() => handleTogglePriority(task)}
                      className={`text-xs px-2 py-0.5 rounded-full border ${getPriorityStyle(task.priority)} hover:opacity-80 transition`}
                    >
                      {getPriorityLabel(task.priority)}
                    </button>
                  </div>

                  {editingDescriptionId === task.id ? (
                    <input
                      value={descriptionDraft}
                      onChange={(e) => setDescriptionDraft(e.target.value)}
                      onBlur={() => saveDescription(task)}
                      onKeyDown={(e) => {
                        if (e.key === "Enter") saveDescription(task);
                        if (e.key === "Escape") cancelEditingDescription();
                      }}
                      autoFocus
                      placeholder="Sem descrição"
                      className="w-full bg-zinc-900 text-zinc-300 text-sm leading-relaxed rounded px-2 py-1 outline-none resize-none"
                    />
                  ) : (
                    <p
                      onClick={() => startEditingDescription(task)}
                      className="text-zinc-400 text-sm leading-relaxed cursor-pointer"
                    >
                      {task.description || "Sem descrição"}
                    </p>
                  )}
                </div>

                <button
                  onClick={() => handleToggleStatus(task)}
                  className="bg-zinc-800 p-2 rounded-lg border border-zinc-700 hover:bg-zinc-700 transition-colors"
                >
                  {getStatusIcon(task.status)}
                </button>
              </div>

              <div className="mt-4 flex items-center gap-4 text-xs text-zinc-500 border-t border-zinc-800 pt-3">
                <div className="flex items-center gap-1.5">
                  {getStatusIcon(task.status)}
                  <span className="uppercase tracking-wider font-semibold">
                    {getStatusText(task.status)}
                  </span>
                </div>

                {task.deadline && (
                  <div className="flex items-center gap-1.5 ml-auto text-zinc-400">
                    <Calendar size={14} />
                    <span> Até {formatDate(task.deadline)}</span>
                  </div>
                )}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default ListTaskPage;
