$(document).ready(function () {
    $(".js-newsletter a.btn").on("click", function () {
		var newsletterWrapper = $(this).closest(".js-newsletter");
		var emailAddress = newsletterWrapper.find("input").val();
		$.ajax({
			url: newsletterWrapper.data("url"),
			data: { emailAddress: emailAddress },
			method: "POST",
			success: function (data) {
			    if (data.success) {
			        gtmDataLayer.push({
			            "event": "newsletter",
			            "newsletterName": "newsletter",
			            'newsletterAction': "subscribe",
			            "shop": "webshop"
			        });
					newsletterWrapper.html(data.message);
				} else {
					newsletterWrapper.find(".error-message").html(data.message);
				}
			}
		});
	});
});