type Match = {
	moves: Moves;
	id: long;
};
type MatchPageData = {
	match: Match;
	authHeader: string;
};
type Moves = { [x: string]: { sign: any } };
