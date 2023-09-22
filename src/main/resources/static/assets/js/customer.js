    window.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.getElementById('customerTableBody');

    fetch('http://localhost:8080/api/customerList') // Assuming this is the correct API endpoint
        .then(response => response.json())
        .then(data => {
            console.log(data)
            if (data.code === 200) {
                data.data.forEach(customer => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${customer.id}</td>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.age}</td>
                        <td>${customer.status}</td>
                        <td>${customer.address}</td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                console.error('Failed to fetch data from the API');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});