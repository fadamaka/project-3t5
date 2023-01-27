<script>
	import Table from '../components/Table.svelte';

	/** @type {import('./$types').PageData} */
	export let data;
	console.log(data.matches);
	let matchesArr = data.matches.map((/** @type {any} */ m) => ({
		'Player 1': m.p1.email,
		'Player 2': m.p2.email,
		status: m.status,
		result: m.result
	}));
</script>

<h1>Welcome to Project 3t5</h1>
<!--><p>You can check out your cart <a href="/cart">here</a></p>-->
<a href="/login">Check your name here</a>

<Table tableData={matchesArr} style="blueTable" />
