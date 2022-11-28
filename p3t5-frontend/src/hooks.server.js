/** @type {import('@sveltejs/kit').Handle} */
export async function handle({ event, resolve }) {
	const { cookies } = event;
	const refresh_token = cookies.get('refresh_token');
	const access_token = cookies.get('access_token');

	if (refresh_token) {
		if (!access_token) {
			const data = await refresh(refresh_token);
			setCookies(cookies, data);
		}
	} else {
		if (event.url.pathname === '/login' && event.url.searchParams.get('code')) {
			const code = event.url.searchParams.get('code');
			const data = await getToken(code);
			setCookies(cookies, data);
			const redirectFrom = cookies.get('redirected_from');
			if (redirectFrom && data.access_token && data.refresh_token) {
				return new Response('Redirect', {
					status: 303,
					headers: {
						Location: 'http://localhost:5173' + redirectFrom,
						'set-cookie': [
							cookies.serialize('access_token', data.access_token, {
								path: '/',
								httpOnly: true,
								maxAge: data.expires_in
							}),
							cookies.serialize('refresh_token', data.refresh_token, {
								path: '/',
								httpOnly: true,
								maxAge: data.refresh_expires_in
							})
						].join(',')
					}
				});
			}
		} else {
			return new Response('Redirect', {
				status: 303,
				headers: {
					Location:
						'http://localhost:8083/auth/realms/p3t5/protocol/openid-connect/auth?response_type=code&client_id=jwtClient&scope=read&redirect_uri=http://localhost:5173/login',
					'set-cookie': 'redirected_from=' + event.url.pathname
				}
			});
		}
	}
	const response = await resolve(event);
	return response;
}

/**
 * @param {string} refresh_token
 */
async function refresh(refresh_token) {
	let formData = buildFormData('refresh_token');
	formData.append('refresh_token', refresh_token);
	return await sendRequest(formData);
}

/**
 * @param {string | null} code
 */
async function getToken(code) {
	let formData = buildFormData('authorization_code');
	formData.append('code', '' + code);
	formData.append('redirect_uri', 'http://localhost:5173/login');
	return await sendRequest(formData);
}

/**
 * @param {import("@sveltejs/kit").Cookies} cookies
 * @param {{ access_token: any; expires_in: any; refresh_token: any; refresh_expires_in: any; }} data
 */
async function setCookies(cookies, data) {
	if (!data.access_token) {
		throw 'WTF';
	}
	cookies.set('access_token', data.access_token, {
		path: '/',
		httpOnly: true,
		sameSite: 'strict',
		maxAge: data.expires_in
	});

	cookies.set('refresh_token', data.refresh_token, {
		path: '/',
		httpOnly: true,
		sameSite: 'strict',
		maxAge: data.refresh_expires_in
	});
}
/**
 * @param {string} type
 */
function buildFormData(type) {
	let formData = new URLSearchParams();
	formData.append('grant_type', type);
	formData.append('client_id', 'jwtClient');
	formData.append('client_secret', 'jwtClientSecret');
	return formData;
}

/**
 * @param {URLSearchParams} formData
 */
async function sendRequest(formData) {
	const options = {
		method: 'POST',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=ISO-8859-1' },
		body: formData.toString()
	};

	let response = await fetch(
		'http://localhost:8083/auth/realms/p3t5/protocol/openid-connect/token',
		options
	);
	return await response.json();
}
