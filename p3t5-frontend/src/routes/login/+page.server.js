/** @type {import('./$types').PageServerLoad} */
export async function load({ url, cookies }) {
	const token = cookies.get('jwt_token') + '';

	return { token: token };
}
