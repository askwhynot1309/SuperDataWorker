    window.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.getElementById('contactTableBody');

    fetch('http://localhost:8080/api/contactList')
        .then(response => response.json())
        .then(data => {
            console.log(data)
            if (data.code === 200) {
                data.data.forEach(contact => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${contact.id}</td>
                        <td>${contact.customer.id}</td>
                        <td>${contact.apartment.id}</td>
                        <td>${contact.startDate}</td>
                        <td>${contact.endDate}</td>
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