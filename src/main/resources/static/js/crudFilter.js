
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
        updateTable(data, filter);
    } catch (error) {
        console.error('Error:', error);
    }
}

let viewId = null;
function updateTable(data, filter) {
    const table = document.getElementById("resultTable");
    table.innerHTML = ""; // Limpiar la tabla antes de actualizar

    // Crear encabezados de la tabla
    const headers = Object.keys(data[0]);

    // Afegim els botons de Modificar
    if (filter === "movie") {
        // headers.push("Relations", "AddDirector", "AddGenre");
        headers.push("Relations", "AddDirector", "AddGenre", "AddKeyword");
    }

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
            if (header === "Relations") {
                // Crear el botón "Relations" y asignarle un evento
                const realtionButton = document.createElement("button");
                realtionButton.textContent = "Relations";
                realtionButton.addEventListener("click", function () {
                    console.log("click a relations");
                    viewId = item.movieId;
                    //relationsMovie(viewId);
                });
                cell.appendChild(realtionButton);
            } else if (header === "AddDirector") {
                // Crear el botón "Relations" y asignarle un evento
                const addDirector = document.createElement("button");
                addDirector.textContent = "AddDirector";
                addDirector.addEventListener("click", function () {
                    console.log("click a addDirector");
                    viewId = item.movieId;
                    //searchPersons();
                    //relationsMovie(viewId);
                    openModalDirector();
                });
                cell.appendChild(addDirector);
            } else if (header === "AddGenre") {
                // Crear el botón "Relations" y asignarle un evento
                const addGenreButton = document.createElement("button");
                addGenreButton.textContent = "AddGenre";
                addGenreButton.addEventListener("click", function () {
                    console.log("click a genre");
                    viewId = item.movieId;
                    openModalGenre();
                    //relationsMovie(viewId);
                });
                cell.appendChild(addGenreButton);
            } else if (header === "AddKeyword") {
                // Crear el botón "Relations" y asignarle un evento
                const addKeywordButton = document.createElement("button");
                addKeywordButton.textContent = "AddKeyword";
                addKeywordButton.addEventListener("click", function () {
                    console.log("click a Keyword");
                    viewId = item.movieId;
                    openModalKeyword();
                    //relationsMovie(viewId);
                });
                cell.appendChild(addKeywordButton);
            } else {
                // Establecer el contenido de la celda para otras columnas
                cell.textContent = item[header];
            }
        });
    });
}

async function relationsMovie(viewId) {
    var addToMovieSelect = document.getElementById("relationSelect").value;
    console.log("Valor seleccionat " + addToMovieSelect);

    let relationSelect = addToMovieSelect.substring(0, 1).toUpperCase() + addToMovieSelect.substring(1);
    const requestBody = {
        movieId: viewId
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch(`/adminArea/movie${relationSelect}`, {
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
        // Mostrar los resultados en el modal
        /*   const relationResults = document.getElementById('relationResults');
            relationResults.innerHTML = '';
   */
        createResultsTable(data);
        //showActorsModal(data, viewId);

    } catch (error) {
        console.error('Error:', error);
    }
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

// Función para crear y mostrar la tabla de resultados en el modal
function createResultsTable(data) {
    const relationResults = document.getElementById('relationResults');
    relationResults.innerHTML = '';

    // Crear la tabla y encabezados
    const table = document.createElement("table");
    const headersRow = table.insertRow();
    Object.keys(data[0]).forEach(header => {
        const th = document.createElement("th");
        th.textContent = header;
        headersRow.appendChild(th);
    });

    // Llenar la tabla con los datos
    data.forEach(item => {
        const row = table.insertRow();
        Object.values(item).forEach(value => {
            const cell = row.insertCell();
            cell.textContent = value;
        });

        // Agregar botón para eliminar
        const deleteButtonCell = row.insertCell();
        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Eliminar";
        deleteButton.addEventListener("click", function () {
            // Aquí puedes agregar la lógica para manejar la eliminación del registro
            // Puedes usar el valor de "item.movieId" o cualquier otra información necesaria
            item.movieId = viewId;

            // Llamada a función para manejar la eliminación
            console.log("Boto de eliminar relacio");
            console.log(item);

            handleDeleteDirector(item);
        });
        deleteButtonCell.appendChild(deleteButton);
    });

    // Agregar la tabla al contenedor
    relationResults.appendChild(table);
}



async function handleDeleteDirector(data) {
    var deleteSelect = document.getElementById("relationSelect").value;
    console.log("Valor seleccionat " + deleteSelect);

    let relationSelect = deleteSelect.substring(0, 1).toUpperCase() + deleteSelect.substring(1);

    const userConfirmed = window.confirm(`Are you sure you want to delete this relation in Movie by id :  ` + data + ' ?');

    if (userConfirmed) {
        try {
            const response = await fetch(`/adminArea/delete${relationSelect}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud: ' + response.statusText);
                }
                return response.text();
            })
                .then(responseData => {
                    // Puedes manejar la respuesta del servidor si es necesario
                    console.log('Actor selection successful');
                    //recarrega la pagina

                    $('#movieModal').modal('hide');
                    //viewActors(movieId);
                    alert(responseData);
                })
        } catch (error) {
            console.error('Error:', error);
        }
    }
}









const keywordInput = document.getElementById("keyword");
keywordInput.addEventListener("input", function () {
    //Envia les noves dades a sendDataFilter perque faci la peticio
    if (keywordInput.value.trim() !== '') {
        sendDataFilter();
    }
})
document.getElementById('page').addEventListener('change', function () {
    sendDataFilter();
});

// Event Listener for Size input
document.getElementById('size').addEventListener('change', function () {
    sendDataFilter();
});



// Función para mostrar el modal
function openRelationsModal() {
    const modal = document.getElementById("relationsModal");
    modal.style.display = "block";
    relationsMovie(viewId);
}

// Función para cerrar el modal
function closeRelationsModal() {
    const modal = document.getElementById("relationsModal");
    modal.style.display = "none";
}

// Evento de clic en el botón "Relations"
document.addEventListener("click", function (event) {
    if (event.target.textContent === "Relations") {
        openRelationsModal();

    }
});

// Evento de clic en el botón de cierre del modal
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("close")) {
        closeRelationsModal();
    }
});




// Añade un evento al botón "Add new person to cast" para abrir el modal de búsqueda de personas
const addPersonButton = document.getElementById('btnPersonSearch');
function openModalDirector() {
    // Limpiar el campo de búsqueda y los resultados
    document.getElementById('personKeyword').value = '';
    document.getElementById('personResults').innerHTML = '';

    // Abrir el modal de búsqueda de personas
    $('#addPersonModal').modal('show');
};



// Añade un evento al botón "Add new person to cast" para abrir el modal de búsqueda de personas
const addGenreButton = document.getElementById('btnGenreSearch');
function openModalGenre() {
    // Limpiar el campo de búsqueda y los resultados
    document.getElementById('genreKeyword').value = '';
    document.getElementById('genreResults').innerHTML = '';

    // Abrir el modal de búsqueda de personas
    $('#addGenreModal').modal('show');
};

const addKeywordButton = document.getElementById('btnKeywordSearch');
function openModalKeyword() {
    // Limpiar el campo de búsqueda y los resultados
    document.getElementById('keywordKeyword').value = '';
    document.getElementById('keywordResults').innerHTML = '';

    // Abrir el modal de búsqueda de personas
    $('#addKeywordModal').modal('show');
};



async function searchPersons() {
    const personKeyword = document.getElementById('personKeyword').value;
    const personPage = document.getElementById('personPage').value;
    const personSize = document.getElementById('personSize').value;

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        keyword: personKeyword,
        personPage: personPage,
        personSize: personSize
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/movieSearch/person', {
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

        // Mostrar los resultados en el modal
        const personResults = document.getElementById('personResults');
        personResults.innerHTML = '';

        data.forEach(person => {
            const listItem = document.createElement('li');
            listItem.textContent = `${person.personName} (${person.personId})`;


            // Añade un botón para agregar la persona al elenco
            const addButton = document.createElement('button');
            addButton.textContent = 'Add';
            addButton.addEventListener('click', function () {
                console.log("View persons " + viewId)
                addDirector(person.personId, viewId);

            });
            listItem.appendChild(addButton);

            personResults.appendChild(listItem);
        });
        // Llamar a la función para actualizar la tabla con los datos recibidos
        // updateTable(data);
    } catch (error) {
        console.error('Error:', error);
    }
}




//FALTA AFEGIR ADD Director
async function addDirector(personId, viewId) {

    const requestBody = {
        personId: personId,
        movieId: viewId
    }
    const userConfirmed = window.confirm(`Are you sure you want to add this actor in Movie by id :  ` + viewId + ' ?');
    if (userConfirmed) {
        try {
            const response = await fetch('/adminArea/addDirector', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            }).then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud: ' + response.statusText);
                }
                return response.text();
            })
                .then(responseData => {
                    alert(responseData)
                    //recarrega la pagina

                    $('#addPersonModal').modal('hide');
                    alert(responseData);
                })
        } catch (error) {
            console.error('Error:', error);
        }

    } else {
        console.log("User canceled gender selection.");
    }
};



async function searchGenres() {
    const genreKeyword = document.getElementById('genreKeyword').value;
    const genrePage = document.getElementById('genrePage').value;
    const genreSize = document.getElementById('genreSize').value;

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        keyword: genreKeyword,
        genrePage: genrePage,
        genreSize: genreSize
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/adminArea/allGenres', {
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

        // Mostrar los resultados en el modal
        const genreResults = document.getElementById('genreResults');
        genreResults.innerHTML = '';

        data.forEach(genre => {
            const listItem = document.createElement('li');
            listItem.textContent = `${genre.genreName} (${genre.genreId})`;


            // Añade un botón para agregar la persona al elenco
            const addButton = document.createElement('button');
            addButton.textContent = 'Add';
            addButton.addEventListener('click', function () {
                console.log("View genre " + viewId)
                addGenre(genre.genreId, viewId);

            });
            listItem.appendChild(addButton);

            genreResults.appendChild(listItem);
        });
        // Llamar a la función para actualizar la tabla con los datos recibidos
        // updateTable(data);
    } catch (error) {
        console.error('Error:', error);
    }
}
//Add genre in Movie
async function addGenre(genreId, viewId) {
    const requestBody = {
        genreId: genreId,
        movieId: viewId
    }
    const userConfirmed = window.confirm(`Are you sure you want to add this Genre in Movie by id :  ` + viewId + ' ?');
    if (userConfirmed) {
        try {
            const response = await fetch('/adminArea/addGenre', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            }).then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud: ' + response.statusText);
                }
                return response.text();
            })
                .then(responseData => {
                    alert(responseData)
                    //recarrega la pagina

                    $('#addGenreModal').modal('hide');
                    alert(responseData);
                })
        } catch (error) {
            console.error('Error:', error);
        }

    } else {
        console.log("User canceled gender selection.");
    }
};


//PERSON SEARCH
const keywordRelationInput = document.getElementById("relationKeyword");
keywordRelationInput.addEventListener("input", function () {
    //Envia les noves dades a sendData perque faci la peticio
    if (keywordRelationInput.value.trim() !== '') {
        relationsMovie(viewId);
        // searchPersons();
    }
})

document.getElementById('relationSelect').addEventListener('change', function () {
    console.log("change");
    relationsMovie(viewId);
    //searchPersons();
});


document.getElementById('relationPage').addEventListener('change', function () {
    relationsMovie(viewId);
    //searchPersons();
});


document.getElementById('relationSize').addEventListener('change', function () {
    relationsMovie(viewId);
    //searchPersons();
});



//PERSON SEARCH
const keywordPersonInput = document.getElementById("personKeyword");
keywordPersonInput.addEventListener("input", function () {
    //Envia les noves dades a searxhPersons perque faci la peticio
    if (keywordPersonInput.value.trim() !== '') {
        searchPersons();
    }
})


document.getElementById('personPage').addEventListener('change', function () {
    searchPersons();
});


document.getElementById('personSize').addEventListener('change', function () {
    searchPersons();
});


//Genre search
const keywordGenreInput = document.getElementById("genreKeyword");
keywordGenreInput.addEventListener("input", function () {
    //Envia les noves dades a searchGenres perque faci la peticio
    if (keywordGenreInput.value.trim() !== '') {
        searchGenres();
    }
})


document.getElementById('genrePage').addEventListener('change', function () {
    searchGenres();
});


document.getElementById('genreSize').addEventListener('change', function () {
    searchGenres();
});


async function searchKeyword() {
    const keywordKeyword = document.getElementById('keywordKeyword').value;
    const keywordPage = document.getElementById('keywordPage').value;
    const keywordSize = document.getElementById('keywordSize').value;

    // Construir el cuerpo de la solicitud POST
    const requestBody = {
        keyword: keywordKeyword,
        keywordPage: keywordPage,
        keywordSize: keywordSize
    };

    try {
        // Realizar la solicitud Fetch al servidor con método POST
        const response = await fetch('/adminArea/keywordName', {
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

        // Mostrar los resultados en el modal
        const keywordResults = document.getElementById('keywordResults');
        keywordResults.innerHTML = '';

        data.forEach(keyword => {
            const listItem = document.createElement('li');
            listItem.textContent = `${keyword.keywordName} (${keyword.keywordId})`;


            // Añade un botón para agregar la persona al elenco
            const addButton = document.createElement('button');
            addButton.textContent = 'Add';
            addButton.addEventListener('click', function () {
                console.log("View keyword " + viewId)
                addGenre(genre.genreId, viewId);
            });
            listItem.appendChild(addButton);

            genreResults.appendChild(listItem);
        });
        // Llamar a la función para actualizar la tabla con los datos recibidos
        // updateTable(data);
    } catch (error) {
        console.error('Error:', error);
    }
}

//Keywords search
const keywordKeywordInput = document.getElementById("keywordKeyword");
keywordKeywordInput.addEventListener("input", function () {
    //Envia les noves dades a sendData perque faci la peticio
    if (keywordKeywordInput.value.trim() !== '') {
        searchKeyword();
    }
})


document.getElementById('keywordPage').addEventListener('change', function () {
    searchKeyword();
});


document.getElementById('keywordSize').addEventListener('change', function () {
    searchKeyword();
});

