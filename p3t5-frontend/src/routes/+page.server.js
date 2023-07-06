/** @type {import('./$types').PageServerLoad} */
export async function load({ url, cookies }) {
	const token = cookies.get('access_token') + '';
	let authHeader = 'Bearer ' + token;
	const options = {
		method: 'GET',
		headers: { Authorization: authHeader }
	};
	let response = await fetch('http://localhost:8081/matches/findAll', options);
	if (response.status === 200) {
		/** @type {Array<Object>} */
		let array = await response.json();
		return { token: '', matches: array };
	} else {
		return { token: '', matches: [] };
	}
}
