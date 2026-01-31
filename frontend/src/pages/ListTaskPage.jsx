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

}

export default ListTaskPage;
