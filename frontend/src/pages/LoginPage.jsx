import { useState } from "react";
import { login } from "../services/authService";
import { useNavigate, Link } from "react-router-dom";

export default function LoginPage() {
  const [form, setForm] = useState({ email: "", password: "" });
  const navigate = useNavigate();

  function handleChange(event) {
    setForm({ ...form, [event.target.name]: event.target.value });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      const { data } = await login(form);
      localStorage.setItem("token", data.token);
      navigate("/home");
    } catch (error) {
      console.log(error);
      alert("Invalid email or password");
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-900 px-4">
      <div className="w-full max-w-md bg-zinc-800 rounded-2xl shadow-xl p-8">
        <h1 className="text-3xl font-bold text-center text-zinc-100 mb-6">
          MyTasks
        </h1>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            name="email"
            placeholder="Email"
            onChange={handleChange}
            className="w-full px-4 py-3 rounded-lg bg-zinc-900 text-zinc-100 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />

          <input
            name="password"
            type="password"
            placeholder="Senha"
            onChange={handleChange}
            className="w-full px-4 py-3 rounded-lg bg-zinc-900 text-zinc-100 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />

          <button
            type="submit"
            className="w-full py-3 rounded-lg bg-zinc-900 hover:bg-indigo-600 text-zinc-100 font-semibold transition"
          >
            Entrar
          </button>
        </form>

        <p className="text-center text-zinc-400 mt-6">
          Não tem conta?{" "}
          <Link to="/register" className="text-indigo-400 hover:underline">
            Criar conta
          </Link>
        </p>
      </div>
    </div>
  );
}
