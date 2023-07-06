export async function load({ params, cookies }) {
	const token = cookies.get('access_token') + '';
	let authHeader = 'Bearer ' + token;
	const options = {
		method: 'GET',
		headers: { Authorization: authHeader }
	};
	let response = await fetch('http://localhost:8081/matches/' + params.slug, options);
	if (response.status === 200) {
		/** @type {Match} */
		let match = await response.json();

		let moves = match.moves;

		return { match, authHeader };
	} else {
		return { heh: 'empty' };
	}
}
