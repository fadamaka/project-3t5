<script>
	// @ts-nocheck

	import { goto } from '$app/navigation';
	/** @type {Array<Object>}*/
	export let tableData = [{ Empty: null, Table: null }];
	/** @type {any}*/
	export let style;
</script>

<table class={style}>
	<thead>
		<tr>
			{#each Object.keys(tableData[0]) as columnHeading}
				<th>{columnHeading}</th>
			{/each}
		</tr>
	</thead>
	<tbody>
		{#each Object.values(tableData) as row}
			<tr on:click={() => goto('match/' + row.id)}>
				{#each Object.values(row) as cell}
					<td>{cell}</td>
				{/each}
			</tr>
		{/each}
	</tbody>
</table>

<style>
	table,
	th,
	td {
		border: 1px solid;
		border-collapse: collapse;
		margin-bottom: 10px;
	}

	table.redTable {
		border: 2px solid #a40808;
		background-color: #eee7db;
		width: 100%;
		text-align: center;
		border-collapse: collapse;
	}
	table.redTable td,
	table.redTable th {
		border: 1px solid #aaaaaa;
		padding: 3px 2px;
	}
	table.redTable tbody td {
		font-size: 13px;
	}
	table.redTable tr:nth-child(even) {
		background: #f5c8bf;
	}
	table.redTable thead {
		background: #a40808;
		background: -moz-linear-gradient(top, #bb4646 0%, #ad2020 66%, #a40808 100%);
		background: -webkit-linear-gradient(top, #bb4646 0%, #ad2020 66%, #a40808 100%);
		background: linear-gradient(to bottom, #bb4646 0%, #ad2020 66%, #a40808 100%);
	}
	table.redTable thead th {
		font-size: 19px;
		font-weight: bold;
		color: #ffffff;
		text-align: center;
		border-left: 2px solid #a40808;
	}
	table.redTable thead th:first-child {
		border-left: none;
	}

	table.blueTable {
		border: 1px solid #1c6ea4;
		background-color: #eeeeee;
		width: 100%;
		text-align: left;
		border-collapse: collapse;
	}
	table.blueTable td,
	table.blueTable th {
		border: 1px solid #aaaaaa;
		padding: 3px 2px;
	}
	table.blueTable tbody td {
		transition: all 300ms;
		font-size: 13px;
	}
	table.blueTable tr:nth-child(even) {
		background: #d0e4f5;
	}
	table.blueTable tr:hover {
		transition: all 300ms;
		background-color: #c8d7e6;
	}

	table.blueTable tr:nth-child(even):hover {
		transition: all 300ms;
		background: #8ed4ed;
	}
	table.blueTable thead {
		background: #1c6ea4;
		background: -moz-linear-gradient(top, #5592bb 0%, #327cad 66%, #1c6ea4 100%);
		background: -webkit-linear-gradient(top, #5592bb 0%, #327cad 66%, #1c6ea4 100%);
		background: linear-gradient(to bottom, #5592bb 0%, #327cad 66%, #1c6ea4 100%);
		border-bottom: 2px solid #444444;
	}

	table.blueTable thead :hover {
		background: #1c6ea4;
		background: -moz-linear-gradient(top, #5592bb 0%, #327cad 66%, #1c6ea4 100%);
		background: -webkit-linear-gradient(top, #5592bb 0%, #327cad 66%, #1c6ea4 100%);
		background: linear-gradient(to bottom, #5592bb 0%, #327cad 66%, #1c6ea4 100%);
		border-bottom: 2px solid #444444;
	}
	table.blueTable thead th {
		font-size: 15px;
		font-weight: bold;
		color: #ffffff;
		border-left: 2px solid #d0e4f5;
	}
	table.blueTable thead th:first-child {
		border-left: none;
	}
</style>
