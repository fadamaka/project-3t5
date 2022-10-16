<script>

/** @type {import('./$types').PageData} */
export let data;
let authHeader = 'Bearer '+data.token;

const options = {
		method: 'GET',
        headers: {Authorization: authHeader}
	};

/**
	 * @type {Promise<String>}
	 */
export let promise;
export let token="I don't know.";

async function getToken(){
    let response =await fetch( 'http://localhost:8081/players/gets', options );
    if(response.status===200){
    let data = await response.json();
    token = data.name?data.name:token;
    return token;}
    else{
        return 'error';
    }
} 

function handleClick() {
    promise = getToken();
}

</script>


<button on:click={handleClick}>
	Say my name!
</button>

<!-- replace this element -->
<p>{token}</p>
<a href="/">Home</a>