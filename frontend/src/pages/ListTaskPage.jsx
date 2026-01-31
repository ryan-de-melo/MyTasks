import { useState, useEffect } from "react";

function ListTaskPage() {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(true);

  useEffect(() => {
    fetchTasks();
  }, []);
  
}

export default ListTaskPage;
