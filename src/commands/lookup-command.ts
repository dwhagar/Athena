import { request } from 'https';
import { CommandContext, CommandOptionType, SlashCommand, SlashCreator } from 'slash-create';

export class LookupCommand extends SlashCommand {
	constructor(creator: SlashCreator) {
		super(creator, {
			name: "lookup",
			description: "Searches the wiki for the specified phrase.",
			options: [
				{
					type: CommandOptionType.STRING,
					name: "query",
					description: "Query to search the wiki for",
					required: true
				}
			],
			guildIDs: null
		});
	}

	private static wikiAdress: string = "https://home.moltenaether.com/wiki/api.php";
	private static searchParameter: string = "?action=opensearch&format=json&redirects=resolve&search=";
	public static searchAdress: string = LookupCommand.wikiAdress + LookupCommand.searchParameter;

	async run(ctx: CommandContext) {
		const textQuery = ctx.options.query;
		const researchResult = await LookupCommand.research(textQuery);
		return LookupCommand.format(researchResult);
	}

	public static async research(textQuery: string)
	{
		return new Promise<string>((resolve, reject) => {
            request(`${this.searchAdress}${encodeURIComponent(textQuery)}`, (response) => {
                let data = '';
                response.on('data', (chunk) => { 
                    data += chunk; 
                })
                response.on('end', ()=> {
                    resolve(data);
                });
            }).end();
        });
	}

	public static format(researchResult: string)
	{
		const researchResultObject = JSON.parse(researchResult);
		const query = researchResultObject[0];
		const keywordsFound: string[] = researchResultObject[1];
		const adressesFound: string[] = researchResultObject[3];
		let formattedObject = `You searched for **${query}**.\n`;
		if(keywordsFound.length == 0 && adressesFound.length == 0)
		{
			formattedObject += `I found nothing.`;
		}
		else 
		{
			keywordsFound.forEach((v, i, array) => {
				formattedObject += `For **${v}** I found <${adressesFound[i]}>\n`
			});
		}

		return formattedObject;
	}
}