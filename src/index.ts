import { Client } from 'discord.js';
import * as readline from 'readline';
import * as fs from 'fs';
import { once } from 'events';
import { LookupCommand } from './commands/lookup-command';

const readlineInterface = readline.createInterface(process.stdin, process.stdout);
const readFileInterface = readline.createInterface(fs.createReadStream('.token'), process.stdout);

async function askDiscordToken(question: string): Promise<string> 
{
	readlineInterface.setPrompt(question);
	readlineInterface.prompt();

	let [promise] = await once(readlineInterface, 'line');
	readlineInterface.close();

	return promise;
}

async function getDiscordToken(): Promise<string>
{
	let [promise] = await once(readFileInterface, 'line');
	readFileInterface.close();
	return promise;
}

async function main() 
{
	const client = new Client();
	// let token = await askDiscordToken('Enter bot token: ');
	let token = await getDiscordToken();

	client.on('ready', () => {
		console.log(`\nLogged in as ${client.user.tag}!`);
	});

	client.on('message', msg => {
		//nested function. call only once console error
		const message = msg.content;
		if(message.startsWith("!!lookup"))
		{
			LookupCommand.handle(msg).catch(reason => console.log("Something went wrong while lookup: " + reason));
		}
	});

	await client.login(token);
	client.user.setActivity('the game.');
}

main().catch(reason => console.error(reason));