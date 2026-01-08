import { Outlet } from "react-router-dom";

function App() {
  return (
    <div className="min-h-screen bg-zinc-900 text-zinc-100">
      <Outlet />
    </div>
  );
}

export default App;
