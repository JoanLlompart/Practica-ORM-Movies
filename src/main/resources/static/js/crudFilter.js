async function sendDataFilter() {
    // Obtener los valores del formulario
    const filter = document.getElementById("filter").value;
    const keyword = document.getElementById("keyword").value;
    const page = document.getElementById("page").value;
    const size = document.getElementById("size").value;

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        filter: filter,
        keyword: keyword,
        page: page,
        size: size
    };

    try {
        const response = await fetch('/adminArea/filter', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        //resposta erronea
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }

        // Obtener los datos de la respuesta JSON
        const data = await response.json();
        console.log(data);
        // Llamar a la función para actualizar la tabla con los datos recibidos
        updateTable(data);
    } catch (error) {
        console.error('Error:', error);
    }
}

function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla
    const headers = Object.keys(data[0]);
    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();
            cell.textContent = item[header];
        });
    });
}


/*
function updateTable(data) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla con clases de Bootstrap
    const headers = Object.keys(data[0]);
    const headerRow = table.insertRow();
    headers.forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        th.classList.add("table-dark"); // Clase de Bootstrap para encabezados oscuros
        headerRow.appendChild(th);
    });

    // Llenar la tabla con los datos y agregar clases de Bootstrap
    data.forEach(item => {
        const row = table.insertRow();
        headers.forEach(header => {
            const cell = row.insertCell();
            cell.textContent = item[header];
            cell.classList.add("table-light"); // Clase de Bootstrap para filas claras
        });
    });

    // Agregar la clase de Bootstrap para tablas
    table.classList.add("table", "table-bordered", "table-striped");
}
*/








const keywordInput = document.getElementById("keyword");
keywordInput.addEventListener("input" , function() {
    //Envia les noves dades a sendDataFilter perque faci la peticio
    if(keywordInput.value.trim() !=='') {
        sendDataFilter();
    }
})
document.getElementById('page').addEventListener('change', function() {
    sendDataFilter();
});

// Event Listener for Size input
document.getElementById('size').addEventListener('change', function() {
    sendDataFilter();
});
