<script>
	import Link from './Link.svelte';
	import Button from './Button.svelte';
	import Input from './Input.svelte';

	let menuOpen = false;
	let inputValue = '';
	$: console.log(inputValue);

	const menuItems = [
		'fadamaka111@gmail.com',
		'Base',
		'Blog',
		'Contact',
		'Custom',
		'Support',
		'Tools',
		'Boats',
		'Cars',
		'Bikes',
		'Sheds',
		'Billygoats',
		'Zebras',
		'Tennis Shoes',
		'New Zealand'
	];
	/**
	 * @type {string | any[]}
	 */
	let filteredItems = [];

	const handleInput = () => {
		return (filteredItems = menuItems.filter((item) =>
			item.toLowerCase().match(inputValue.toLowerCase())
		));
	};
</script>

<section class="dropdown">
	<Button on:click={() => (menuOpen = !menuOpen)} {menuOpen} />

	<div id="myDropdown" class:show={menuOpen} class="dropdown-content">
		<Input bind:inputValue on:input={handleInput} />
		<!-- MENU -->
		{#if filteredItems.length > 0}
			{#each filteredItems as item}
				<Link link={item} />
			{/each}
		{:else}
			{#each menuItems as item}
				<Link link={item} />
			{/each}
		{/if}
	</div>
</section>

<style>
	.dropdown {
		position: relative;
		display: inline-block;
	}

	.dropdown-content {
		display: none;
		position: absolute;
		background-color: #f6f6f6;
		min-width: 230px;
		border: 1px solid #ddd;
		z-index: 1;
	}

	/* Show the dropdown menu */
	.show {
		display: block;
	}
</style>
