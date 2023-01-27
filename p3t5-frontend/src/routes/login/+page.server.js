/** @type {import('./$types').PageServerLoad} */
export async function load({ url, cookies }) {
	const token = cookies.get('access_token') + '';

	return { token: token, matches: [] };
}
