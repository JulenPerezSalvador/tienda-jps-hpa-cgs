document.addEventListener("DOMContentLoaded", () => {
    const btnModo = document.getElementById("toggle-modo");
    const body = document.body;

    // 1. Comprobar la memoria del navegador al cargar la página
    const modoGuardado = localStorage.getItem("modo-tienda");
    if (modoGuardado === "oscuro") {
        body.classList.add("dark-mode");
        if(btnModo) btnModo.textContent = "☀️ Modo Claro";
    }

    // 2. Darle función al botón
    if (btnModo) {
        btnModo.addEventListener("click", () => {
            body.classList.toggle("dark-mode");

            if (body.classList.contains("dark-mode")) {
                localStorage.setItem("modo-tienda", "oscuro");
                btnModo.textContent = "☀️ Modo Claro";
            } else {
                localStorage.setItem("modo-tienda", "claro");
                btnModo.textContent = "🌙 Modo Oscuro";
            }
        });
    }
});