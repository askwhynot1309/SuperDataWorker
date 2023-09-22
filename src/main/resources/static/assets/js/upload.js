$(document).ready(function() {
    function handleFormSubmission(formId, apiUrl) {
        $(formId).submit(function(event) {
            event.preventDefault();

            var formData = new FormData($(this)[0]);

            $.ajax({
                url: apiUrl,
                type: 'POST',
                data: formData,
                async: false,
                success: function(response) {
                    alert('Message: ' + response.message);
                },
                cache: false,
                contentType: false,
                processData: false
            });
        });
    }

    handleFormSubmission("#uploadCustomerForm", "/api/customer/upload");
    handleFormSubmission("#uploadApartmentForm", "/api/apartment/upload");
    handleFormSubmission("#uploadContractForm", "/api/contact/upload");
});