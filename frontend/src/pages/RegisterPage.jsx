import { useState } from "react";
import { register } from "../services/authService";
import { useNavigate, Link } from "react-router-dom";

export default function RegisterPage() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      await register(form);
      navigate("/login");
    } catch {
      alert("Erro ao criar conta");
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-900 px-4">
      <div className="w-full max-w-md bg-zinc-800 rounded-2xl shadow-xl p-8">
        <h1 className="text-3xl font-bold text-center text-white mb-6">
          Criar conta
        </h1>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            name="name"
            placeholder="Nome"
            onChange={handleChange}
            className="w-full px-4 py-3 rounded-lg bg-zinc-900 text-zinc-100 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />

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
            className="w-full py-3 rounded-lg bg-zinc-900 hover:bg-indigo-600 text-white font-semibold transition-colors"
          >
            Criar conta
          </button>
        </form>

        <p className="text-center text-zinc-400 mt-6">
          Já tem conta?{" "}
          <Link to="/login" className="text-indigo-400 hover:underline">
            Entrar
          </Link>
        </p>
      </div>
    </div>
  );
}
