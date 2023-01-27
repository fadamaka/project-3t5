<script>
	/** @type {import('./$types').PageData} */
	export let data;
	let authHeader = 'Bearer ' + data.token;

	const options = {
		method: 'GET',
		headers: { Authorization: authHeader }
	};

	/**
	 * @type {Promise<String>}
	 */
	export let promise;
	export let name = "I don't know.";

	async function getName() {
		let response = await fetch('http://localhost:8081/players/gets', options);
		if (response.status === 200) {
			let data = await response.json();
			name = data.name ? data.name : name;
			return name;
		} else {
			return 'error';
		}
	}

	function handleClick() {
		promise = getName();
	}
</script>

<button on:click={handleClick}> Say my name! </button>

<!-- replace this element -->
<p>{name}</p>
<p>{data.token}</p>
<a href="/">Home</a>
