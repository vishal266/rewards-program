$( document ).ready(function() {

    // GET All customer transactions
    $("#getAllCustomerId").click(function(event){
        event.preventDefault();
        doGetAllCustomerTransactions();
    });

    function doGetAllCustomerTransactions(){
        $.ajax({
            type : "GET",
            url : window.location + "/api/v1/transactions",
            success: function(result){
                if(result){
                    $('#getResultDiv ul').empty();
                    $.each(result, function(i, customerTransaction){
                        var customer = " <p style='background-color:#2e6da4; color:white; padding:20px 20px 20px 20px'> Customer with Id = "
                            + customerTransaction.customerId + "  amount = " + customerTransaction.amount + "  rewards = " +
                            customerTransaction.rewardPoints + "</p>";
                        $('#getResultDiv .list-group').append(customer)
                    });
                    console.log("Success: ", result);
                }else{
                    $("#getResultDiv").html("<strong style='background-color:#b0502b; color:white; padding:20px 20px 20px 20px'>Error</strong>");
                    console.log("Fail: ", result);
                }
            },
            error : function(e) {
                $("#getResultDiv").html("<strong style='background-color:#b0502b; color:white; padding:20px 20px 20px 20px'>Error</strong>");
                console.log("ERROR: ", e);
            }
        });
    }

    // GET customer transactions for GIVEN customer id
    $("#getAllTransactionsForCustomerId").click(function(event){
        event.preventDefault();
        doGetCustomerTransactionsForCustomerId();
    });

    // DO GET
    function doGetCustomerTransactionsForCustomerId() {
        var formData = {
            customerId: $("#transactionForCustomerId").val()
        }
        if (!formData.customerId || formData.customerId == '') {
            window.alert("Customer Id cannot be blank!");
        } else {
            $.ajax({
                type: "GET",
                url: window.location + "/api/v1/transactions?customerId=" + formData.customerId,
                success: function (result) {
                    if (result) {
                        $('#getDivForCustomerId ul').empty();
                        $.each(result, function (i, customerTransaction) {
                            var customer = " <p style='background-color:#2e6da4; color:white; padding:20px 20px 20px 20px'> Customer with Id = "
                                + customerTransaction.customerId + "  amount = " + customerTransaction.amount + "  rewards = " +
                                customerTransaction.rewardPoints + "</p>";
                            $('#getDivForCustomerId .list-group').append(customer)
                        });
                        console.log("Success: ", result);
                    } else {
                        $("#getDivForCustomerId").html("<strong style='background-color:#b0502b; color:white; padding:20px 20px 20px 20px'>Error</strong>");
                        console.log("Fail: ", result);
                    }
                },
                error: function (e) {
                    $("#getDivForCustomerId").html("<strong style='background-color:#b0502b; color:white; padding:20px 20px 20px 20px'>Error</strong>");
                    console.log("ERROR: ", e);
                }
            });
        }
    }
})