import { useNavigate } from "react-router-dom";

function HomePage() {
     const navigate = useNavigate();

     function gotoCreate() {
        navigate("/create");
     }
     

     return (
       <div className="min-h-screen flex items-center justify-center bg-zinc-900 px-4">
         <div className="w-full max-w-md bg-zinc-800 rounded-2xl shadow-lg p-8 text-center">
           <h1 className="text-4xl font-bold text-zinc-100 mb-4">MyTasks</h1>

           <p className="text-zinc-400 mb-8">
             Organize suas tarefas de forma simples
           </p>

           <div className="flex-col flex gap-4">
             <button
               onClick={() => gotoCreate()}
               className="w-full mt-2 rounded-lg bg-zinc-900 py-2 font-medium text-white hover:bg-indigo-600 transition-colors"
             >
               Criar tarefa
             </button>
           </div>
         </div>
       </div>
     );
}

export default HomePage;