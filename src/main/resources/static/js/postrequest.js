$( document ).ready(function() {

    // CREATE transaction entry for customer
    $("#transactionForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        submitCustomerTransaction();
    });


    function submitCustomerTransaction(){
        // data from form for POST request
        var formData = {
            customerId : $("#customerId").val(),
            amount :  $("#amount").val()
        }
        if(!formData.customerId || formData.customerId == '' || !formData.amount || formData.amount == '' || formData.amount < 0) {
            window.alert("Invalid customer Id and/or transaction amount");
        } else {
            // Submit POST request to backend
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: window.location + "/api/v1/transactions",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function (result) {
                    if (result) {
                        $("#postResultDiv").html("<p style='background-color:#69b08b; color:white; padding:20px 20px 20px 20px'>" +
                            "Transaction created successfully! <br>" +
                            "Customer Id = " + result.customerId +
                            "  amount = " + result.amount +
                            "  rewards earned = " + result.rewardPoints + "</p>");
                    } else {
                        $("#postResultDiv").html("<strong style='background-color:#b0502b; color:white; padding:20px 20px 20px 20px'>Error</strong>");
                    }
                    console.log(result);
                },
                error: function (e) {
                    window.alert("Error!");
                    console.log("ERROR: ", e);
                }
            });
        }
        // Reset FormData after Posting
        resetData();
    }

    function resetData(){
        $("#customerId").val("");
        $("#amount").val("");
    }
})