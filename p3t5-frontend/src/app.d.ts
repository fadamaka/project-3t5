// See https://kit.svelte.dev/docs/types#app
// for information about these interfaces
// and what to do when importing types
declare namespace App {
	// interface Locals {}
	interface PageData {
		token: String;
		matches: Array<Object>;
	}
	interface Match {
		moves: Array<Object>;
	}
	// interface Error {}
	// interface Platform {}
}
