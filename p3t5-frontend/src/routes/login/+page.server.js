/** @type {import('./$types').PageServerLoad} */
export async function load({ url }) {
	let code = url.searchParams.get('code');
	console.log(code);
	let formData = new URLSearchParams();

	formData.append('grant_type', 'authorization_code');
	formData.append('code', '' + code);
	formData.append('client_id', 'jwtClient');
	formData.append('redirect_uri', 'http://localhost:5173/login');
	formData.append('client_secret', 'jwtClientSecret');

	const options = {
		method: 'POST',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=ISO-8859-1' },
		body: formData.toString()
	};

	let response = await fetch(
		'http://localhost:8083/auth/realms/p3t5/protocol/openid-connect/token',
		options
	);
	let data = await response.json();
	let token = data.access_token;

	return { token: token };
}
