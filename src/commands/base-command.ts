import { Message, GuildMember } from 'discord.js';

export class BaseCommand 
{
	public static prefix: string = '!!';
	protected static permissions: number[] = [];

	public static async handle(msg: Message) 
	{
		await msg.reply('Wrong command.');
	}

	public static isExecutableBy(user: GuildMember): boolean 
	{

		return !this.permissions ? true : user.hasPermission(this.permissions);
	}

	public static stripMentions(content: string)
	{
		var newContent = content.split(' ');
		return newContent.filter(v => {
			return !v.startsWith('<@') && !v.endsWith('>') && !v.startsWith(this.prefix);
		});
	}
}