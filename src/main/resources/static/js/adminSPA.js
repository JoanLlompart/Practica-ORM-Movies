function mostrarSeccion(seccion) {
    var secciones = document.querySelectorAll('.section');
    secciones.forEach(function (element) {
        element.classList.remove('active-section');
    });
    document.getElementById(seccion).classList.add('active-section');
}
/*

function mostrarSeccion() {
    var select = document.getElementById("crud");
    var seccionSeleccionada = select.value;

    var secciones = document.getElementsByClassName("section");
    for (var i = 0; i < secciones.length; i++) {
        secciones[i].classList.remove("active-section");
    }

    var seccionActual = document.getElementById(seccionSeleccionada);
    seccionActual.classList.add("active-section");
}
*/