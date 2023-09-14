<script>
	/** @type {Array<Object>}*/
	export let tableData = [{ Empty: null, Table: null }];
	export let handleClick = async (x, y) => {};
</script>

<table>
	<tbody>
		{#each Object.values(tableData) as row}
			<tr>
				{#each Object.values(row) as cell}
					<td on:click={() => handleClick(cell.coords[0], cell.coords[1])}>{cell.sign}</td>
				{/each}
			</tr>
		{/each}
	</tbody>
</table>

<style>
	table,
	td {
		border: 1px solid;
		border-collapse: collapse;
	}

	td:hover {
		transition: all 300ms;
		background-color: #aaa192;
	}

	td {
		width: 20px;
		height: 20px;
		overflow: hidden;
		display: inline-block;
		white-space: nowrap;
	}

	tr {
		white-space: nowrap;
	}

	table {
		border: 2px solid #a40808;
		background-color: #eee7db;
		width: 100%;
		text-align: center;
		border-collapse: collapse;
	}
</style>
