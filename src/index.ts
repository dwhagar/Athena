import { Client, GatewayDispatchEvents, IntentsBitField } from 'discord.js';
import { LookupCommand } from './commands/lookup-command.js';
import * as dotenv from 'dotenv';
import { GatewayServer, SlashCreator } from 'slash-create';

const commands = [
	LookupCommand
];
async function main() 
{
	const result = dotenv.config()

	if (result.error) {
		throw result.error
	}
	const client = new Client({ intents: [IntentsBitField.Flags.Guilds] });
	let token = process.env.DISCORD_TOKEN;
	let appID = process.env.DISCORD_APPID;
	let publicKey = process.env.PUBLICKEY;

	client.on('ready', () => {
		console.log(`\nLogged in as ${client.user.tag}!`);
	});

	const creator = new SlashCreator({
		applicationID: appID,
		publicKey: publicKey,
		token: token,
		client,
	});

	creator
		.withServer(
			new GatewayServer((handler) =>
				client.ws.on(GatewayDispatchEvents.InteractionCreate, handler)
			)
		)
		.registerCommands(commands)
		.syncCommands();

	await client.login(token);
	client.user.setActivity('the game.');
}

main().catch(reason => console.error(reason));