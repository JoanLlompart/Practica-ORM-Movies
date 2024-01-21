
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
    confirm()
    const isoCodeValue = document.getElementById('countryIsoCode').value;
    const nameValue = document.getElementById('countryName').value;

    if (isoCodeValue && nameValue) {
        // Mostrar confirmación antes de enviar la solicitud
        const userConfirmed = window.confirm('Are you sure you want to add this new Country?');

        if (userConfirmed) {
            const data = {
                isoCode: isoCodeValue,
                name: nameValue,
            };

            fetch('/adminArea/add', {
                method: 'POST',
                body: JSON.stringify(data),
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error en la solicitud: ' + response.statusText);
                    }
                    return response.text();
                })
                .then(responseData => {
                    // Verificar si el servidor envió un mensaje y mostrarlo con un alert
                    alert(responseData);
                    console.log(responseData);
                })
                .catch(error => {
                    console.error('Error al enviar datos:', error);
                });
        } else {
            // El usuario ha hecho clic en "Cancelar" en la confirmación
            console.log('Operación cancelada por el usuario.');
        }
    }
});
