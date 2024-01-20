

async function sendData() {
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
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestBody)
        });

        // Verificar el estado de la respuesta
        if (!response.ok) {
            throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }

        // Obtener los datos de la respuesta JSON
        const data = await response.json();

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


const keywordInput = document.getElementById("keyword");
keywordInput.addEventListener("input" , function() {
    //Envia les noves dades a sendData perque faci la peticio
    if(keywordInput.value.trim() !=='') {
        sendData();
    }
})



/*

ORIGINAL I FUNCIONAL
function sendData() {
    var form = document.getElementById('formFilter');
    var filter = document.getElementById('filter').value;
    var keyword = document.getElementById('keyword').value;
    var formData = {
        filter: filter,
        keyword: keyword
        }
        fetch('/movieSearch', {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            // Borrar la taula actual de dades per mostrar la nova
            var tableBody = document.querySelector('.table tbody');
            tableBody.innerHTML = "";
            
             // Llenar la tabla con los nuevos datos
            data.moviesFind.forEach(movie => {
                var newRow = tableBody.insertRow(tableBody.rows.length);
                newRow.insertCell(0).textContent = movie.movieId;
                newRow.insertCell(1).textContent = movie.title;
                newRow.insertCell(2).textContent = movie.releaseDate;
                newRow.insertCell(3).textContent = movie.voteAverage;
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
*/