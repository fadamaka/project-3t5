<script>
	import { onMount } from 'svelte';
	import Playfield from '../../../components/Playfield.svelte';

	/** @type {MatchPageData} */
	export let data;
	export let movesArr = getMovesArr(data.match.moves);
	console.log(data);

	onMount(() => {
		const interval = setInterval(refresh, 3000);

		return () => clearInterval(interval);
	});

	/**
	 * @param {number} x
	 * @param {number} y
	 */
	async function move(x, y) {
		let response = await fetch('http://localhost:8081/matches/' + data.match.id + '/move', {
			method: 'POST',
			body: JSON.stringify({ x, y }),
			// @ts-ignore
			headers: {
				Authorization: data.authHeader,
				'Content-Type': 'application/json'
			}
		});
		if (response.status === 200) {
			let data = await response.json();
			console.log(JSON.stringify(data));
			await refresh();
		} else {
			console.log(await response.json());

			console.error('error');
		}
	}
	/**
	 * @param {{ [x: string]: { sign: any; }; }} moves
	 */
	function getMovesArr(moves) {
		let coords = Object.keys(moves).map((e) => JSON.parse(e));
		let xCoords = coords.map((e) => e[0]);
		let yCoords = coords.map((e) => e[1]);

		let min = Math.min(Math.min(...xCoords), Math.min(...yCoords)) - 10;
		let max = Math.max(Math.max(...xCoords), Math.max(...yCoords)) + 10;
		let movesArr = [];
		for (let i = max; i >= min; i--) {
			let row = [];
			for (let j = max; j >= min; j--) {
				const coords = '[' + j + ',' + i + ']';

				row.push({ sign: moves[coords]?.sign || '', coords: [j, i] });
			}
			movesArr.push(row);
		}
		return movesArr;
	}
	async function refresh() {
		const options = {
			method: 'GET',
			headers: { Authorization: data.authHeader }
		};
		let response = await fetch('http://localhost:8081/matches/' + data.match.id, options);
		if (response.status === 200) {
			/** @type {Match} */
			let match = await response.json();

			let moves = match.moves;
			movesArr = getMovesArr(moves);
		} else {
			return 'error';
		}
	}
</script>

<a href="/">Home</a>
<button on:click={refresh}> Refresh </button>
<Playfield tableData={movesArr} handleClick={move} />
