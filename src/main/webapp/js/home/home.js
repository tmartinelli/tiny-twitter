$(document).ready( function() {

	search();
	
});

function search() {
	$("#searchInput").autocomplete({
		serviceUrl: "/tiny-twitter/users",
		paramName: "name",
		dataType: "json",
		transformResult: function(response) {
	        return {
	            suggestions: $.map(response.users, function(user) {
	                return { value: user.name, data: user.id.toString() };
	            })
	        };
	    },		
		onSelect: function (suggestion) {
			var url = "/tiny-twitter/users/" + suggestion.data;
			$(location).attr("href", url);
		}
	});
}