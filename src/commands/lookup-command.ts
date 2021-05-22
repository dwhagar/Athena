import { BaseCommand } from './base-command';
import { Message } from 'discord.js';
import { request } from 'https';

export class LookupCommand extends BaseCommand
{
	private static wikiAdress: string = "https://home.moltenaether.com/wiki/api.php";
	private static searchParameter: string = "?action=opensearch&format=json&redirects=resolve&search=";
	public static searchAdress: string = LookupCommand.wikiAdress + LookupCommand.searchParameter;

	public static async handle(msg: Message)
	{
		msg.channel.startTyping();
		const textQuery = msg.content.replace("!!lookup", "");
		if(!textQuery)
		{
			msg.channel.send("Enter a keyword.");
		}
		else {
			const researchResult = await this.research(textQuery);
			msg.channel.send(this.format(researchResult));
		}
		msg.channel.stopTyping();
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