function submitDomainForm() {
	var name = $('#domainName').val().trim();
	if (name.length == 0) {
		alert('Please enter domain name');
		$('#domainName').focus();
		return false;
	}
	return true;
};