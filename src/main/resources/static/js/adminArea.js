
const operationSelect = document.getElementById('operationSelect');
const selectedSection = document.getElementById('selectedSection');

operationSelect.addEventListener('change', (event) => {
    const selectedOption = event.target.value;

    if (selectedOption === 'add') {
        mostrarSeccion('añadir');
    } else if (selectedOption === 'update') {
        mostrarSeccion('actualizar');
    } else if (selectedOption === 'delete') {
        mostrarSeccion('borrar');
    }
});

function mostrarSeccion(seccion) {
    var secciones = document.querySelectorAll('.section');
    secciones.forEach(function (element) {
        element.classList.remove('active-section');
    });
    document.getElementById(seccion).classList.add('active-section');
}


const addButton = document.getElementById('addButton');
addButton.addEventListener('click', (event) => {
    const isoCodeValue = document.getElementById('isoCode').value;
    const nameValue = document.getElementById('name').value;

    if (isoCodeValue && nameValue) {
        const data = {
            isoCode: isoCodeValue,
            name: nameValue,
        };
        console.log(data);

        fetch('/adminArea/add', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(responseData => {
                console.log(responseData);
            })
            .catch(error => {
                console.error('Error al enviar datos:', error);
            });
    }
});
