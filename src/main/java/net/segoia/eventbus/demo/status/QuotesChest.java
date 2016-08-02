package net.segoia.eventbus.demo.status;

import java.util.ArrayList;
import java.util.List;

public class QuotesChest {
    
    private static String quotesStr="You can't legislate morality... but it seems that morons can be legislators. \n" + 
    	"Lottery: A tax on people who are bad at math. \n" + 
    	"These are questions for action, not speculation, which is idle. 	-- Noam Chomsky \n" + 
    	"If you think education is expensive try Ignorance. 	-- Derek Bok, president of Harvard \n" + 
    	"Photons have neither morals nor visas. 	-- Dave Farber \n" + 
    	"Maturity is not a factor of the games we play but the occasions we play them! \n" + 
    	"Design a system an idiot can use and only an idiot will want to use it. \n" + 
    	"It is much more rewarding to do more with less. 	-- Donald Knuth \n" + 
    	"Inside every small problem is a big one trying to get government funding. \n" + 
    	"Huh? Windows was designed to keep the idiots away from Unix so we could hack in peace. Let's not break that. 	-- Tom Christiansen \n" + 
    	"Listen to the gods speak. `cat /proc/kcore > /dev/audio` \n" + 
    	"The windows registry is dead ground which microsoft keeps trying to plant in. \n" + 
    	"If you aim the gun at your foot and pull the trigger, it's UNIX's job to ensure reliable delivery of the bullet to where you aimed the gun (in this case, Mr. Foot). (Terry) \n" + 
    	"Linux - In a world without fences, who needs Gates? \n" + 
    	"What goes up, must come down. Ask any system administrator. \n" + 
    	"Friends don't let friends buy proprietary software \n" + 
    	"All my life, I have done what was expected of me. It never occurred to me to do otherwise. Now, I wonder what might have been. The future frightens us, the past tempts us, and our lives slip away, lost in the terrible moment in between. 	-- Londo, Babylon 5 \n" + 
    	"Insanity is part of the times. You must learn to embrace the madness; let it fire you. 	-- Londo, Babylon 5 to Vir Cotto \n" + 
    	"Humility is for the non-conceited. \n" + 
    	"The future has arrived; it's just not evenly distributed. 	-- William Gibson \n" + 
    	"Shallit's Razor: Never attribute to conspiracy what may be adequately explained by stupidity or incompetence. \n" + 
    	"\"Love is only a dirty trick played on us to achieve the continuation of the species\" 	-- W. Somerset Maugham \n" + 
    	"\"Do, or do not. There is no try.\" 	-- Yoda \n" + 
    	"-- Backwards compatible -- If it's not backwards it's not compatible. \n" + 
    	"Pride goeth before a fall. 	-- Babylon 5 \n" + 
    	"Trouble will come in its own time, it always does. But that's tomorrow. Give me today and I will be happy. 	-- Sheridan, Babylon 5 (After the shadow war was over) \n" + 
    	"We can't really predict the future at all. All we can do is invent it. \n" + 
    	"Can money pay for all the days I lived half a sleep? 	-- Primitive Radio Gods - Standing by a broken phone booth \n" + 
    	"\"Socialism is a philosophy of failure.  It is the creed of ignorance and the gospel of envy.  It's inherent virtue is the equal sharing - of misery.\" 	-- Winston Churchill \n" + 
    	"You can judge the quality of your life by how often you notice the enjoyment of the little things. \n" + 
    	"Mary had a crypto key, she kept it in escrow, and everything that Mary said, the Feds were sure to know. 	-- Sam Simpson, July 9, 1998 \n" + 
    	"Just because your paronoid doesn't mean someone isn't out to get you. \n" + 
    	"Nearly all men can stand adversity, but if you want to test a man's character, give him power. 	-- Abraham Lincon \n" + 
    	"It's dangerous to be right when the government is wrong. 	-- Voltaire \n" + 
    	"A secret between two is secret of God. A secret between three is everybody's secret. 	-- French Proverb \n" + 
    	"Magic - Product without process 	-- John W. Cambell \n" + 
    	"\"On a day no different than the one now dawning, Leonardo drew the first strokes of the Mona Lisa, Shakespeare wrote the first words of Hamlet, Beethoven began work on his Ninth Symphony and Einstein discovered the theory of relativity. What are you going to do today?\" \n" + 
    	"The easiest way to predict the future is to invent it. 	-- Xerox Parc motto \n" + 
    	"Life moves pretty fast. If you don't stop to look around once in a while you'll miss it. 	-- Faris Beulers day off. \n" + 
    	"As folks might have suspected, not much survives except roaches, and they don't carry large enough packets fast enough...         -- About the Internet and nuclear war. \n" + 
    	"It often requires more courage to dare to be right than it does to fear to be wrong. 	-- Lincon \n" + 
    	"Capitol Punishment: When big theives hang the little ones. \n" + 
    	"Don't stop think about tomorrow. Don't stop it will soon be here. 	-- Song I heard on the radio - (Yesterday's gone?) \n" + 
    	"\"I do not feel obliged to believe that same God who endowed us with sense, reason, and intellect, had intended for us to forgo their use.\" 	-- Galileo \n" + 
    	"Indifference will certainly be the downfall of mankind, but who cares? \n" + 
    	"Has it ever occurred to you that God might be a committee? 	-- Jubal Harshaw \n" + 
    	"\"You've got to be willing to read other people's code, then write your own, then have other people review your code.\" 	-- Bill Gates \n" + 
    	"When Gandhi was asked at the end of his life what his biggest disappointment was, he replied, \"The hard-heartedness of the intellectual class.\" \n" + 
    	"After the game, the king and the pawn go in the same box. 	-- Italian proverb \n" + 
    	"\"by perserverance the snail reached the Ark\" 	-- Charles Sprugeon \n" + 
    	"Life is a sexually transmitted disease. \n" + 
    	"Remember that what's inside of you doesn't matter because nobody can see it. \n" + 
    	"If God didn't want us to eat animals, he wouldn't have made them out of meat! \n" + 
    	"Nick the Greek's Law of Life: All things considered, life is 9 to 5 against. \n" + 
    	"Those who can laugh at themselves will never cease to be amused. \n" + 
    	"Beware the day when any of the people involved want to stop the dialogue, not the day when the dialogue is passionate. 	-- Pohl <pohl@screaming.org> posted to Slashdot (Mar 24, 1999) \n" + 
    	"Pessimism is self-fulfilling. \n" + 
    	"Enjoy your work, because those who do not enjoy what they do, are condemmed to live out of place for the whole eternity. 	-- Nataneal \n" + 
    	"The race is long but in the end it is only with yourself. 	-- Everybody's Free (Song) \n" + 
    	"The only meaning in life is what we (all conscious beings, not just humans) give it. 	-- demona \n" + 
    	"<demona> always let your adversary be the one to get upset \n" + 
    	"\"Pity the meek, for they shall inherit the earth\" \n" + 
    	"But if the answer isn't violence, neither is your silence. 	-- Ich Bin Ein Auslander - Pop Will Eat Itself \n" + 
    	"To persuade your enemy to (retreat) before the fight is to defeat them even before the battle begins.  An enemy made ally is no longer an enemy. 	-- Sun Tzu, The Art of War \n" + 
    	"\"Every new beginning comes from some other beginning's end.\" 	-- Semisonic's \"Closing Time\" \n" + 
    	"If you can't be with the one you love, then shag the one your with. 	-- Austin Powers - The Spy who shagged me \n" + 
    	"Efficiency can magnify good, but it magnifies evil just as well. So, we should not be surprised to find that modern electronic communication magnifies stupidity as *efficiently* as it magnifies intelligence. 	-- Robert A. Matern \n" + 
    	"The church saves sinners, but science seeks to stop their manufacture. 	-- Elbert Hubbard \n" + 
    	"\"Have a clue.  They're cheap.  All you have to do is pay ttention.\" 	- Unknown \n" + 
    	"\"Those who desire to give up Freedom in order to gain Security, will not have, nor do they deserve, either one.\" 	-- Thomas Jefferson \n" + 
    	"Men are born ignorant, not stupid; they are made stupid by education. 	-- Bertrand Russell \n" + 
    	"A picture is worth a thousand words. It just takes up more bandwidth. \n" + 
    	"Software isn't software without source code. 	-- NASA \n" + 
    	"Money will say more in one moment than the most eloquent lover can in years. \n" + 
    	"Memory is like an orgasm. It's a lot better if you don't have to fake it. 	-- Seymour Cray commenting on virtual memory \n" + 
    	"\"There are two things that are infinite; Human stupidity and the universe.  And I'm not sure about the universe.\" 	-- Albert Einstein \n" + 
    	"If you know the enemy and know yourself, you need not fear the result of a hundred battles. If you know yourself but not the enemy, for every victory gained you will also suffer a defeat. If you know neither the enemy nor yourself, you will succumb in every battle. 	-Sun Tzu - The Art of War \n" + 
    	"Actors will happen even in the best-regulated families. \n" + 
    	"\"... the deep things in science are not found because they are useful; they are found because it was possible to find them.\" 	--R.O. \n" + 
    	"\"You want to kiss the sky? Better learn how to kneel.\" 	- U2 \n" + 
    	"The way of the warrior If you love, love without reservation. If you fight, fight without fear. 	- Captain Sheridan's Father - Babylon 5 \n" + 
    	"\"So much has been lost, so much forgotton. So much pain, so much blood, and for what, I wonder? The past tempts us, the present confuses us, and the future frightens us. And out lives slip away, moment by moment, lost in that vast terrible inbetween. But there is still time to sieze that one last fragile moment. To choose something better. To make a differance.\" 	-- Centauri Emperor on last visit to B5 - Babylon 5 \n" + 
    	"\"Good is never good enough when you dream of being the best.\" \n" + 
    	"\"He who slings mud, looses ground.\" 	-- Posted on Slashdot by Oestergaard \n" + 
    	"Man is most nearly himself when he achieves the seriousness of a child at play. 	-- Heraclitus, Greek philosopher (500 B.C.) \n" + 
    	"\"I know not with what weapons World War III will be fought, but World War IV will be fought with sticks and stones.\" 	-- Albert Einstein \n" + 
    	"\"Paranoia is just Reality at a higher resolution!\" \n" + 
    	"Rome did not create a great empire by having meetings, they did it by killing all who opposed them. 	-- Elliot Lee (GNOME guy, probably not orignal) \n" + 
    	"\"Men occasionally stumble over the truth, but most of them pick themselves up and hurry off as if nothing had happened.\" 	-- Winston Churchill \n" + 
    	"Understanding is a 3 edged sword. Thier side, your side and the truth. 	- Vorlon Saying, Babylon 5 \n" + 
    	"Books, Manuals, Directives, Regulations. The geometries that circumscribe your working life draw norrower and norrower until nothing fits inside them anymore. 	- Byron, Babylon 5 \n" + 
    	"Do you know what this is? No I can see that you don't. You have that vacant look in your eyes that says hold my head to your ear you'll hear the sea. 	-- Londo Malari, Babylon 5 \n" + 
    	"There's only one way to have a happy marriage and as soon as I learn what it is I'll get married again. 	-- Clint Eastwood \n" + 
    	"\"The greatest dangers to liberty lurk in insidious encroachment by men of zeal, well-meaning but without understanding.\" 	-- Louis Brandeis - (Former Supreme Court Justice) \n" + 
    	"\"A candle loses nothing by lighting another candle.\" 	-- Unknown \n" + 
    	"Be not anxious about what you have, but about what you are. 	-- Pope St. Gregory I \n" + 
    	"The price of freedom is eternal vigilance. \n" + 
    	"Outside of their particular area of expertise scientists are just as dumb as the next person. 	-- Richard Feynmann \n" + 
    	"\"This generation may be the one that will face Armageddon.\" 	-- Ronald Reagan, \"People\" magazine, December 26, 1985 \n" + 
    	"\"The longer I live, the more I realize the impact of attitude on life. Attitude, to me, is more important than Facts. It is more important than the past, than education, than money, than circumstances, than Failures, than successes, than what other people think or say or do. It is more important than appearance, giftedness or skill. It will make or break a company.., a church.., a home. The remarkable thing is we have a choice everyday regarding the attitude we will embrace for that day. We cannot change our past.. we cannot change the fact that people will act in a certain way. We cannot change the inevitable. The only thing we can do is play on the one string we have, and that is our attitude... I am convinced that life is 10% what happens to me and 90% how I react to it. And so it is with you.. we are in charge of our attitudes\". 	-- Charles Swindoll \n" + 
    	"Those who do not understand Unix are condemned to reinvent it, poorly. 	-- Henry Spencer \n" + 
    	"Programming, is like sex. Make one mistake, and you support it for the rest of your life. \n" + 
    	"\"a bad reputation is hard to shake, a good reputation is hard to maintain\" 	-- Anonymous \n" + 
    	"complex pick-up line: If I were to ask you for sex, would your answer be the same as the answer to this question? \n" + 
    	"I'm not sure which upsets me more: that people are so unwilling to accept responsibility for their own actions, or that they are so eager to regulate everyone else's. \n" + 
    	"What luck for the rulers that men do not think. 	-- Adolf Hitler \n" + 
    	"\"The universe is not required to be in perfect harmony with human ambition.\" 	-- Carl Sagan \n" + 
    	"\"message passing as the fundamental operation of the OS is just an excercise in computer science masturbation. It may feel good, but you don't actually get anything DONE.\" 	- Linus talking about Micro-Kernels and the EROS OS. \n" + 
    	"Bad men live that they may eat and drink, whereas good men eat and drink that they may live. 	-- Socrates \n" + 
    	"\"Unix-to-Unix Copy Program;\" said PDP-1.  \"You will never find a more wretched hive of bugs and flamers.  We must be cautious.\" 	-- DECWARS \n" + 
    	"The great masses of the people ... will more easily fall victims to a big lie than to a small one. 	-- Adolf Hitler, \"Mein Kampf\" \n" + 
    	"Nothing is illegal if one hundred businessmen decide to do it. 	-- Andrew Young \n" + 
    	"We have guided missiles, and misguided men\" 	-- Martin Luther King \n" + 
    	"Don't vote - it only encourages them! \n" + 
    	"Tell a man there are 300 billion stars in the universe and he'll believe you. Tell him a seat has wet paint on it and he'll have to touch it to be sure. \n" + 
    	"\"I may disagree vehemently with what you say, but I will fight to the death for your right to say it.\" 	-- Francois-Marie Arouet de Voltaire \n" + 
    	"Issawi's Laws of Progress:         The Course of Progress:                 Most things get steadily worse.         The Path of Progress:                 A shortcut is the longest distance between two points. \n" + 
    	"heaven must be running unix, how else do u explain so much uptime? \n" + 
    	"I _am_ pragmatic. That which works, works, and theory can go screw itself. However, my pragmatism also extends to maintainability, which is why I also want it done well. 	-- Linus \n" + 
    	"Nothing focuses the mind better than the constant sight of a competitor who wants to wipe you off the map. 	-- Wayne Calloway \n" + 
    	"\"A little science estranges a man from God. A lot of science brings him back.\" 	-- Francis Bacon \n" + 
    	"At the source of every error which is blamed on the computer you will find at least two human errors, including the error of blaming it on the computer. \n" + 
    	"99 little bugs in the code, 99 bugs in the code,           fix one bug, compile it again...           101 little bugs in the code.... \n" + 
    	"Today is the first day of the rest of your life. \n" + 
    	"\"If the fool would persist in his folly he would become wise.\" 	-- William Blake, \"Proverbs of Hell,\" 1793 \n" + 
    	"The more corrupt the state, the more numerous the laws. 	-- Tacitus, 56-120 A.D. \n" + 
    	"\"Bombay is 250ms from New York in the new world order\" 	-- Alan Cox \n" + 
    	"\"There are two major products that come out of Berkeley: LSD and UNIX. We don't believe this to be a coincidence.\" 	-- Jeremy Anderson \n" + 
    	"Religion stops a thinking mind. \n" + 
    	"\"If I had eight hours to chop down a tree, I'd spend seven of them sharpening my axe.\" 	-- Abraham Lincoln \n" + 
    	"Poverty is the mother of invention. \n" + 
    	"In God we Trust -- all others must submit an X.509 certificate. 	-- Charles Forsythe <forsythe@alum.mit.edu> \n" + 
    	"\"Violence is the last refuge of the incompetent\" 	-- Isaac Asimov \n" + 
    	"If violence is the last refuge of the incompetent, religion is the second last. 	-- Dan & Kier \n" + 
    	"Windows 2000: Designed for the Internet. The Internet: Designed for UNIX. \n" + 
    	"Censorship rests on the child's delusion that \"If I shut my eyes so I can't see it, it isn't there\". \n" + 
    	"G-d is alive. Magic is afoot. G-d is afoot. Magic is alive. 	-- Leonard Cohen \n" + 
    	"What if life is the second chance? 	-- Anonymous \n" + 
    	"We build our computers the way we build our cities -- over time, without a plan, on top of ruins. 	-- Ellen Ullman (www.salon.com article) \n" + 
    	"software engineering is not about right and wrong but only better and worse 	-- Ellen Ullman (www.salon.com article) \n" + 
    	"Men willingly believe what they wish. 	-- Julius Caesar \n" + 
    	"Once you stop dismissing something as unattainable, then you start working on its development. 	-- Jerome Pearson \n" + 
    	"Sufficiently advanced stupidity is indistinguishable from malice. \n" + 
    	"the principle cause of problems is solutions. 	-- Eric Serveraid \n" + 
    	"\"reality is something, that once you stope believing in it, it doesn't go away.\" 	-- Philip K Dick, \"Valis\" \n" + 
    	"\"Science is a way of thinking that helps you not to fool yourself.\" 	-- Carl Sagan \n" + 
    	"Be courageous; it's the only place left uncrowded. 	-- Anita Roddick \n" + 
    	"Happiness isn't something you experience, it's something you remember. 	-- Oscar Levant \n" + 
    	"The future is no place to place your better days. 	-- Dave Matthews Band \n" + 
    	"Software designers are so infatuated with the fact that they can, that they don't stop to think if they should. \n" + 
    	"say what you mean, and mean what you say. \n" + 
    	"\"reason over passion\" 	-- Pierre Elliott Trudeau's motto \n" + 
    	"Freedom requires duty \n" + 
    	"whereof one cannot speak, thereof one must be silent 	-- Ludwig Wittgenstein \n" + 
    	"\"The Pythagorean Theorem employed 24 words, the Lord's Prayer has 66 words, Archimedes Principle has 67words, the 10 Commandments have 179 words, the Gettysburg Address had 286 words, the Declaration of Independence, 1,300 words and finally the European Commission's regulation on the sale of cabbage: 26,911 words.\" \n" + 
    	"\"Amateurs hack systems, professionals hack people\" 	-- Bruce Schneier \n" + 
    	"\"Programming is an art form that fights back\" 	-- Saige on SlashDot \n" + 
    	"Majority, n.: That quality that distinguishes a crime from a law. \n" + 
    	"He who knows others is wise. He who knows himself is enlightened. 	-- Lao Tsu \n" + 
    	"God grant me the courage not to give up what I think is right, even though I think it is hopeless. 	-- Admiral Chester W. Nimitz \n" + 
    	"\"Understanding. A cerebral secretion that enables one having it to know a house from a horse by the roof on the house, It's nature and laws have been exhaustively expounded by Locke, who rode a house, and Kant, who lived in a horse.\" 	-- Ambrose Bierce \n" + 
    	"There are no physicists in the hottest parts of hell, because the existence of a \"hottest part\" implies a temperature difference, and any marginally competent physicist would immediately use this to run a heat engine and make some other part of hell comfortably cool.  This is obviously impossible. 	-- Richard Davisson \n" + 
    	"Luck, like a Russian car, generally only works if you push it. 	-- Regalian, in My Hero (Tom Holt) \n" + 
    	"If God had wanted us to vote, he would have provided candidates. 	-- Dorothy Day \n" + 
    	"Documentation is like sex, when its good, its very very good. And when its bad, its better than nothing. \n" + 
    	"Good judgment comes from experience.  Unfortunately, the experience usually comes from bad judgment. \n" + 
    	"Money is how people with no talent keep score. 	-- Michael Hart, Brief History of the Internet, 1995 \n" + 
    	"\"Those who do not do politics will be done in by politics.\" 	-- French Proverb \n" + 
    	"Disraeli was pretty close: actually, there are Lies, Damn lies, Statistics, Benchmarks, and Delivery dates. \n" + 
    	"A man is as sane as he is dangerous to his environment \n" + 
    	"Focus on the dream, not the competition. 	-- Nemesis Racing Team motto \n" + 
    	"\"The whole problem with the world is that fools and fanatics are always so certain of themselves, but wiser people so full of doubts.\" 	-- Bertrand Russell \n" + 
    	"\"When I get a little money, I buy books; and if any is left, I buy food and clothes.\" 	-- Erasmus, Opus Epistolarum, 1529 \n" + 
    	"Anything free is worth what you pay for it. \n" + 
    	"\"It seems certain that much of the success of Unix follows from the readability, modifiability, and portability of its software.\" 	-- Dennis M. Ritchie, September, 1979 \n" + 
    	"One is never deceived, one deceives oneself. \n" + 
    	"The greatest of all faults is to be conscious of none 	-- Thomas Carlyle 1795-1881 \n" + 
    	"Goldenstern's Rules: 	(1) Always hire a rich attorney 	(2) Never buy from a rich salesman. \n" + 
    	"\"One mode to rule them all and in the darkness bind them...\" 	-- Tolkien \n" + 
    	"Once is a coincidence, twice is statistically improbable, three times is enemy action. 	-- Story of Jeff Part XII (www.securityportal.com) \n" + 
    	"Beware the fury of a patient man. \n" + 
    	"Like a version 1.1, touch(1)ed for the very first time... \n" + 
    	"\"Under Capitalism, man exploits man. Under Communism, it's the other way around\" \n" + 
    	"Fourth Law of Thermodynamics:  If the probability of success is not almost one, it is damn near zero. 	-- David Ellis \n" + 
    	"Save energy: be apathetic. \n" + 
    	"\"Don't tell me I'm burning the candle at both ends -- tell me where to get more wax!!\" \n" + 
    	"I've begun to wonder if we wouldn't also regard spelunkers as desperate criminals if AT&T owned all the caves. 	-- John Perry Barlow \n" + 
    	"I'm a member of that half of the human race which is inclined to divide the human race into two kinds of people.  My dividing line runs between the people who crave certainty and the people who trust chance. 	-- John Perry Barlow \n" + 
    	"\"Testing can prove the presence of bugs, but not their absence.\" 	-- Dykstra \n" + 
    	"There are two ways of constructing a software design; One way is to make it so simple that there are obviously no deficiencies, and the other way is to make it so complicated that there are no obvious deficiences. 	-- C. A. R. Hoare \n" + 
    	"Is is always time to do the right thing. 	-- Martin Luther King \n" + 
    	"Solve 90% of the problem as simply as you can, and then remove the other 10% from the problem requirements. 	-- Marshall Rose \n" + 
    	"The universe doesn't always give you what you want. It gives you what you need. \n" + 
    	"\"If you think technology can solve your security problems, then you don't understand the problems and you don't understand the technology.\" 	-- Bruce Schneier, Secrets and Lies \n" + 
    	"You know, the mark of intelligence is realizing when you're making the same mistake over and over and over again, and not hitting your head in the wall five hundred times before you understand that it's not a clever thing to do. 	-- Linus Torvalds \n" + 
    	"Common sense is the collection of prejudices acquired by age eighteen. 	-- Albert Einstein \n" + 
    	"If you are out to describe the truth, leave elegance to the tailor. 	-- Albert Einstein \n" + 
    	"Imagination is more important than knowledge. 	-- Albert Einstein \n" + 
    	"\"Memory is like gasoline. You use it up when you are running. Of course you get it all back when you reboot...\" 	-- Actual explanation obtained from the Micro$oft help desk. \n" + 
    	"In a time of universal deceit, telling the truth is a revolutionary act. 	-- George Orwell \n" + 
    	"The trouble with computers is that they do what you tell them, not what you want. 	-- D. Cohen \n" + 
    	"Until I loved, life had no beauty; I did not know I lived until I had loved. 	-- Theodor Korner \n" + 
    	"So, make a real effort to avoid getting sucked into all the expensive lifestyle habits of typical Americans.  Because if you do that, then people with the money will dictate what you do with your life. 	--Richard Stallman \n" + 
    	"Personally, I'm always ready to learn, although I do not always like being taught. 	-- Sir Winston Churchill \n" + 
    	"\"My mother said to me, if you become a soldier, you'll be a general, if you become a monk, you'll end up as the pope. Instead I became a painter and wound up as Picasso.\" 	-- Pablo Picasso \n" + 
    	"\"The people who get on in this world are the people who get up and look for the circumstances they want, and, if they can´t find them, make them.\" 	-- George Bernard Shaw \n" + 
    	"\"All men by nature desire to know.\" 	-- Aristotle \n" + 
    	"\"Experience is that, which you get after you need it.\" \n" + 
    	"\"This year will go down in history. For the first time, a civilized nation has full gun registration! Our streets will be safer, our police more efficient, and the world will follow our lead into the future.\" 	-- Adolf Hitler, 1935 \n" + 
    	"When I consider the small span of my life  absorbed in the eternity of all time, or the small part of space which I can touch or see engulfed by the infinite immensity of spaces that I know  not and that know me not, I am frightened and astonished to see myself here instead of there... now instead of then. 	-- Blaise Pascal \n" + 
    	"\"Silly hacker, root is for administrators\" 	-- Unknown \n" + 
    	"\"Be good, and you will be lonesome.\" 	-- Mark Twain \n" + 
    	"A physicist is an atom's way of knowing about atoms. \n" + 
    	"The events which transpired five thousand years ago; Five years ago or five minutes ago, have determined what will happen five minutes from now; five years From now or five thousand years from now. All history is a current event. 	-- Dr John Henrik Clake \n" + 
    	"Opportunities multiply as they are seized. 	-- Sun Tzu \n" + 
    	"Give a man a fish, and he will eat for a day. Teach him how to fish and he will stop bothering you. 	-- Unknown \n" + 
    	"Unix is simple. It just takes a genius to understand its simplicity. \n" + 
    	"\"I think we agnostics need a term for a holy war too. I feel all left out.\" 	-- George Lebl \n" + 
    	"\"When he pretends to flee, do not pursue.\" 	-- Sun Tzu, The Art of War (\"Maneuver\", Statement 27) \n" + 
    	"\"If you continue to think the way you've always thought, then you will continue to get what you always got!\" \n" + 
    	"Never be afraid to try something new. Remember, amateurs built the ark. Professionals built the Titanic \n" + 
    	"\"Patriotism is the last refuge of a scoundrel.\" 	-- Samuel Johnson \n" + 
    	"\"The appeal to force is the abandonment of reason.\" 	-- Introduction to Logic, 11th Ed. 	-- Irving M. Copi & Carl Cohen \n" + 
    	"\"The fact that an opinion has been widely held is no evidence whatever that is not uterly absurd; indeed, in view of the silliness of the majority of mankind, a wide-spread belief is more likely to be foolish than sensible.\" 	-- Bertrans Russell \n" + 
    	"There is nothing so useless as doing efficiently that which should not be done at all. 	-- Peter Drucker \n" + 
    	"\"No great civilisation likes forests.\" 	-- K.F. O'Connor \n" + 
    	"We are most probably here for local information-gathering and local-Universe problem-solving in support of the integrity of eternally regenerative Universe. 	-- R. Buckminster Fuller \n" + 
    	"Be master of your petty annoyances and conserve your energies for the big, worthwhile things. It isn't the mountain ahead that wears you out - it's the grain of sand in your shoe. \n" + 
    	"\"If you're smart, you'll be humble. There always is somebody who hasn't read a book and knows twice as much as you do.\" 	-- David Duchovny in Readers' Digest \n" + 
    	"\"I believe in true love. But I am easily satisfied.\" 	-- Miguel de Icaza \n" + 
    	"\"If only you could see what I have seen with your eyes\" 	-- Roy Batty \n" + 
    	"\"Never argue with idiots, they'll only drag you down to their level and beat you with experience.\" \n" + 
    	"\"Biology is the only science in which multiplication means the same thing as division.\" 	-- Greg Leblanc \n" + 
    	"Whenever you find that you are on the side of the majority, it is time to reform. 	-- Mark Twain \n" + 
    	"\"If you don't have a hammer, hack it\" \n" + 
    	"\"when you eliminate the impossible, whatever remains, however improbable, must be the truth.\" 	-- Sherlock Holmes \n" + 
    	"'The project can be correct, it can be fast, or it can be on time, pick two' \n" + 
    	"\"the good is the enemy of the best\" 	-- Unknown Proverb (found in reference to protocol design) \n" + 
    	"\"I never let my schooling get in the way of my education.\" 	-- Mark Twain \n" + 
    	"\"No cause, no God, no abstract idea can justify the mass slaughter of innocents.\" 	-- Edward Said \n" + 
    	"\"Evil will always triumph over good, because good is dumb.\" 	-- Dark Helmet, Spaceballs \n" + 
    	"\"Never give up. Never give up. Never ever give up.\" 	-- Winston Churchill \n" + 
    	"Originality is undetected plagiarism. 	-- Dean W. R. Inge \n" + 
    	"Procrastination is great. It gives me a lot more time to do things that I'm never going to do. \n" + 
    	"If you're not paranoid, you just haven't been paying attention. \n" + 
    	"\"Until they become conscious they will never rebel, and until after they have rebelled they cannot become conscious.\" \n" + 
    	"Everything should be made as simple as possible, but no simpler. 	-- Albert Einstein \n" + 
    	"The rhythm method: it sounds good on paper, just like lighting yourself on fire to keep warm. \n" + 
    	"Now what is history? It is the centuries of systematic explorations of the riddle of death, with a view to overcoming death. That's why people discover mathematical infinity and electromagnetic waves, that's why they write symphonies. 	-- Boris Pasternak, Doctor Zhivago \n" + 
    	"\"The only freedom which deserves the name is that of pursuing our own good, in our own way, so long as we do not attempt to deprive others of theirs, or impede their efforts to obtain it.\" 	-- John Stuart Mill \n" + 
    	"One cannot make an omelette without breaking eggs -- but it is amazing how many eggs one can break without making a decent omelette. 	-- Professor Charles P. Issawi \n" + 
    	"\"All men of conscience or prudence ply to windward, to maintain their wars to be defensive.\" 	-- Roger Williams \n" + 
    	"\"I invented the term Object-Oriented, and I can tell you I did not have C++ in mind.\" 	-- Alan Kay \n" + 
    	"All in favor of losing their rights, please do nothing. \n" + 
    	"It took me fifteen years to discover that I had no talent for writing, but I couldn't give it up because by that time I was too famous. 	-- Robert Benchley \n" + 
    	"I see a dark sail on the horizon Set under a dark cloud that hides the sun Bring me my Broadsword and clear understanding \n" + 
    	"In my view, those of us who teach science to nonscience majors must continually encourage them to base their beliefs on evidence rather than mere prior preference. 	-- William H. Ingham (Physicstoday.org) \n" + 
    	"The probability that we may fail in the struggle ought not to deter us from the support of a cause we believe to be just. 	-- Abraham Lincoln \n" + 
    	"\"We don't like their sound, and guitar music is on the way out.\" 	-- Decca Recording Co. rejecting the Beatles, 1962. \n" + 
    	"\"I never think of the future - it comes soon enough.\" 	-- Albert Einstein \n" + 
    	"Unix is not \"just\" an Operating System. Unix is a way of life. \n" + 
    	"I am convinced that if we are to get on the right side of the world revolution, we as a nation must undergo a radical revolution of values. We must rapidly begin the shift from a thing-oriented society to a person-oriented society. When machines and computers, profit motives and property rights, are considered more important than people, the giant triplets of racism, extreme materialism, and militarism are incapable of being conquered. 	-- Martin Luther King \n" + 
    	"\"Everybody thinks of changing humanity and nobody thinks of changing himself\" 	-- Leo Tolstoy. \n" + 
    	"\"There is nothing as deceptive as an obvious fact.\" 	--Sir Arthur Conan Doyle \n" + 
    	"Certainly there are things in life that money can't buy, But it's very funny -- did you ever try buying them without money? 	-- Ogden Nash \n" + 
    	"We may often do as we please -- but we cannot please as we please. 	-- Bertrand Russell \n" + 
    	"\"Human beings act intelligently only after they have exhausted the alternatives\" 	-- Abba Eban \n" + 
    	"Why live outside the US? Do you want health care or safe food products or democracy or something? They're all overrated. Stay for the excellent cable TV. 	-- Ian McKellar (GNOME Developer) \n" + 
    	"\"When there's public debate and mass hysteria, that's when the patches roll in.\" 	-- Michael Meeks (GNOME Developer) \n" + 
    	"Debugging is anticipated with distaste, performed with reluctance, and bragged about forever. 	-- Mads Villadsen \n" + 
    	"\"Science helps a lot, but people built perfectly good brick walls long before they knew why cement works.\" 	-- Alan Cox \n" + 
    	"Never put off till run-time what you can do at compile-time. 	-- D. Gries \n" + 
    	"\"Don't bother using an over-simplified metaphor to prove me wrong. It means you don't understand my point.\" 	-- NanoG \n" + 
    	"Only presidents, editors, and people with tapeworms have the right to use the editorial \"we.\" \n" + 
    	"\"In a gold rush it's the people who sell the spades who make the money first\" \n" + 
    	"\"Boys do what they can; men do what they want\" 	-- Project Pat \n" + 
    	"Commities are good optics, poor executors. \n" + 
    	"In passing I draw attention to another English expression which often occurs in Dutch texts: \"the real world\". In Dutch -- and I am afraid not in Dutch alone -- its usage is almost always a symptom of a violent anti-intellectualism. 	-- E. W. Dijkstra \n" + 
    	"\"Extraordinary claims require extraordinary evidence\" 	-- Carl Sagan. \n" + 
    	"If you analyse anything, you destroy it. 	-- Arthur Miller \n" + 
    	"\"Business is a good game-lots of competition and a minimum of rules. You keep score with money.\" 	-- Nolan Bushnell \n" + 
    	"\"Spend your 'different points' wisely.\" 	-- Havoc Pennington \n" + 
    	"A thorough software professional is one who when his wife yells at him \"goto hell\", worries more about the goto. \n" + 
    	"A celibate clergy is an especially good idea, because it tends to suppress any hereditary propensity toward fanaticism. 	-- Carl Sagan \n" + 
    	"\"UNIX was not designed to stop you from doing stupid things, because that would also stop you from doing clever things.\" 	-- Doug Gwyn \n" + 
    	"Confidence is the feeling you have before you understand the situation. \n" + 
    	"Perfection is reached, not when there is no longer anything to add, but when there is no longer anything to take away. 	-- Antoine de Saint-Exupery \n" + 
    	"\"If you can't make it good, make it big\" \n" + 
    	"\"Reusing pieces of code is like picking off sentences from other people's stories and trying to make a magazine article.\" It might make your point, but not very well. 	-- Bob Frankston \n" + 
    	"\"Why was God able to create the universe in only seven days? Because he didn't have an installed base to deal with.\" \n" + 
    	"The better educated will always rule the less educated. 	-- Sheldon J. Pacotti \n" + 
    	"You can't see the world through a mirror. 	-- Avril Lavigne (Too much to ask) \n" + 
    	"Those who dream by day are cognizant of many things which escape those who dream only by night. 	-- Edgar Allan Poe \n" + 
    	"'The most incomprehensible thing about the world is that it is comprehensible.' 	-- Albert Einstein \n" + 
    	"\"Fragmentation is like classful addressing -- an interesting early architectural error that shows how much experimentation was going on while IP was being designed.\" 	-- Paul Vixie \n" + 
    	"Dinosaurs aren't extinct. They've just learned to hide in the trees. \n" + 
    	"\"The greatest evils in the world will not be carried out by men with guns, but by men in suits sitting behind desks\" 	-- C. S. Lewis \n" + 
    	"The point is, that geeks are not necessarily the outcasts society often believes they are. The fact is that society isn't cool enough to be included in our activities. \n" + 
    	"fork() is the Unix programmer's hammer. Because it's available, every problem looks like a nail. \n" + 
    	"\"if it is relevant there is always somebody else out there.\" 	-- Linus Torvalds \n" + 
    	"\"Programming is one of the most difficult branches of applied mathematics; the poorer mathematicians had better remain pure mathematicians.\" 	-- Edsger W. Dijkstra \n" + 
    	"The question of whether a computer can think is no more interesting than the question of whether a submarine can swim. 	-- E.W. Dijkstra \n" + 
    	"Computer science is no more about computers than astronomy is about telescopes. 	-- E.W. Dijkstra \n" + 
    	"Hardware is the part of a computer system that can be kicked and software is the part that can only be screamed at. 	-- Unknown \n" + 
    	"Computers are useless. They can only give you answers. 	-- Pablo Picasso (1881-1973) \n" + 
    	"\"In theory, there is no difference between theory and practice. In practice, there is.\" 	-- Yogi Berra \n" + 
    	"We're sysadmins. To us, data is a protocol-overhead. \n" + 
    	"Don't put off for tomorrow what you can do today, because if you enjoy it today you can do it again tomorrow. \n" + 
    	"\"you can have peace.  or you can have freedom. don't ever count on having both at once.\" 	-- LL (RAH) \n" + 
    	"\"Some humans would do anything to see if it was possible to do it. If you put a large switch in some cave somewhere, with a sign on it saying \"End-of-the-World Switch. PLEASE DO NOT TOUCH,\" the paint wouldn't even have time to dry.\"                                 -- Terry Pratchett                                    (Thief of Time) \n" + 
    	"I am concerned about any program, any piece of hardware, any treaty, any law that treats me as a consumer, not a citizen 	-- dowobeha on Slashdot \n" + 
    	"Those who do not understand end-to-end are doomed to reimplement it, poorly. 	-- Greg Maxwell on NANOG \n" + 
    	"\"Laws are like cobwebs; strong enough to detain only the weak, and too weak to hold the strong\" 	-- Anacharsis \n" + 
    	"\"The internet is a reflection of our society and that mirror is going to be reflecting what we see,\" he said. \"If we do not like what we see in that mirror the problem is not to fix the mirror, we have to fix society.\" 	-- Vint Cerf \n" + 
    	"If you have the ivory tower view that the internet is good only if everything on it is good you are mistaken.\" 	-- Vint Cerf \n" + 
    	"A deep, unwavering belief is a sure sign you're missing something... \n" + 
    	"You can make any computation go faster if you don't care if it gives the right answer. \n" + 
    	"\"We act as though comfort and luxury were the chief requirements of life, when all that we need to make us happy is something to be enthusiastic about.\" 	-- Albert Einstein \n" + 
    	"\"Preferences are bad solutions to performance problems.\" 	-- Havoc Pennington \n" + 
    	"\"Stupid people surround themselves with smart people. Smart people surround themselves with smart people who disagree with them.\" 	-- Aaron Sorkin \n" + 
    	"Self documenting code:     #define ITERATE for     #define THROUGH (int i     #define THIS = 0;     #define LOOP i <     #define TIMES ; ++i) \n" + 
    	"    ITERATE THROUGH THIS LOOP 3 TIMES     {         doit();     } 	-- Post by \"astrosmash\" on Slashdot. \n" + 
    	"We want to use computers, because they're so fast, but we don't have to think like they do. 	-- Bill Richter on comp.lang.scheme discussing pointers. \n" + 
    	"One of the fundamental problems is that security is very hard. And what makes it hard is that it’s a negative deliverable. You really don’t know when you have it. You only find out belatedly when you’ve lost it. 	-- Jeff Schiller (Network Manager at MIT) \n" + 
    	"\"The mark of an immature man is that he wants to die nobly for a cause, while the mark of the mature man is that he wants to live humbly for one.\" 	--W. Stekel \n" + 
    	"\"Debugging is twice as hard as writing the code in the first place. Therefore, if you write the code as cleverly as possible, you are, by definition, not smart enough to debug it.\" 	-- Brian W. Kernighan \n" + 
    	"\"Push the responsibility to the edges, where it belongs\". 	-- Steve Bellovin \n" + 
    	"Think of the Nets infrastructure as a source of natural building resources. Linux is not growing on the trees - it is the trees. 	-- Doc Searls \n" + 
    	"Digital files cannot be made uncopyable, any more than water can be made not wet. 	-- Bruce Schneier \n" + 
    	"life, n.: A whim of several billion cells to be you for a while. \n" + 
    	"For those who live in the past, there is no future. \"If you take no part in the design of your future, it will be designed for you by others.” 	-- Edward de Bono \n" + 
    	"\"Any person can invent a security system so clever that she or he can't think of how to break it.\" 	-- Schneier's Law \n" + 
    	"\"the Web is the sum of all human knowledge plus porn.\" 	-- Ron Gilbert \n" + 
    	"For however much the state may gain by not having to fund roads on its own, society would lose in aggregate if the open commons of transportation were lost. 	-- Lawrence Lessig - The Future of Ideas - Page 174 \n" + 
    	"I have made this letter longer than usual, only because I have not had the time to make it shorter. 	-- Blaise Pascal (translation from the original French) \n" + 
    	"everyone connected to the internet acts globally.  it is wildly foolish to think locally. 	-- Paul Vixie \n" + 
    	"Indeed one of the best ways to deflect attacks is to make it look like they're succeeding. It's the software equivalent of playing dead. 	-- Joel Spolsky \n" + 
    	"Morality must always be based on practicality. 	-- Baron Vladimir Harkonnen \n" + 
    	"Build a man a fire, he's warm for a day.  Set a man on fire, he's warm for the rest of his life... 	-- Terry Pratchett \n" + 
    	"Nearly every electrical engineer believes deep in his heart that he is better at writing computer software than any computer programmer, and can show as proof the fact that he has written a number of small applications, each of which was done quickly, easily, and exactly met his needs.     -- Unknown source \n" + 
    	"I'm waking up to say I tried, instead of waking up to another TV guide. 	-- Avril Lavigne (Mobile) \n" + 
    	"\"Snow and adolescence are the only problems that disappear if you ignore them long enough.\" \n" + 
    	"\"First they ignore you, then they laugh at you, then they fight you, then you win.\" 	-- Ghandhi \n" + 
    	"common programmer thought pattern: there are only three numbers: 0, 1, and n. 	-- Joel Spolsky (User Interface Design for Programmers, Ch 7). \n" + 
    	"Only failure makes us experts. 	-- Theo de Raadt \n" + 
    	"If you look at software today, through the lens of the history of engineering, it's certainly engineering of a sort - but it's the kind of engineering that people without the concept of the arch did.  Most software today is very much like an Egyptian pyramid with millions of bricks piled on top of each other, with no structural integrity, but just done by brute force and thousands of slaves. 	-- Alan Kay, ACM Queue, Vol 2. No. 9 \n" + 
    	"Occupying a third place in human intellectual culture, computing is not bound by the need to describe what does exist (as in natural science) or what can be built in the real world (as in engineering). 	-- John Crowcroft, Communications of the ACM, Vol 48. No. 2 \n" + 
    	"When a stupid man is doing something is he ashamed of, he always declares that it is his duty. 	-- George Bernard Shaw \n" + 
    	"When you hear voices in your head that tell you to shoot the pope, do you do what they say? Same thing goes for customers and managers. They are the crazy voices in your head, and you need to set them right, not just blindly do what they ask for. 	-- Linus Torvalds \n" + 
    	"Just because the person you are arguing with is wrong, it does not therefore follow that you are right. 	-- Unknown \n" + 
    	"Those who cannot remember the past are condemned to repeat it. 	-- George Santayana \n" + 
    	"You have reached the pinnacle of success as soon as you become uninterested in money, compliments or publicity. 	-- Thomas Wolfe, The Sun, July 2005. \n" + 
    	"We reject kings, presidents and voting. We believe in rough consensus and running code. 	-- Dave Clark \n" + 
    	"A complex system that works is invariably found to have evolved from a simple system that worked….A complex system designed from scratch never works and cannot be patched up to make it work. You have to start over, beginning with a working simple system. 	-- John Gall \n" + 
    	"There is no future in which bits will be harder to copy than they are today ... Any business model that based on the idea that bits will be harder to copy is doomed. 	-- Cory Doctorow (2006 RedHat summit in Nashville) \n" + 
    	"Analogy is a poor tool for reasoning, but a good analogy can be very effective in developing intuition. 	-- Barbara Simons and Jim Horning 	(Communications of the ACM, Sept 2005, Inside Risks) \n" + 
    	"If we knew what it was we were doing, it would not be called research, would it? 	-- Albert Einstein \n" + 
    	"If at first, the idea is not absurd, then there is no hope for it. 	-- Albert Einstein \n" + 
    	"blogging is a way of sending emails that go \"cc:world\" 	-- Doc Searls \n" + 
    	"The function of genius is not to give new answers, but to pose new questions which time and mediocrity can resolve. 	--Hugh Trevor-Roper in \"Men and Events\" \n" + 
    	"I will, in fact, claim that the difference between a bad programmer and a good one is whether he considers his code or his data structures more important. Bad programmers worry about the code. Good programmers worry about data structures and their relationships. 	-- Linus Torvalds \n" + 
    	"It is difficult to get a man to understand something when his salary depends upon his not understanding it. 	-- Upton Sinclair \n" + 
    	"Great programmers learn how to program their tools, not just use them. 	-- Steve Yegge \n" + 
    	"the way to do good basic design isn't actually to be really smart about it, but to try to have a few basic concepts. 	-- Linux Torvalds \n" + 
    	"So, it’s important for us to acknowledge that we’re prone to be conservative, and in turn surround ourselves with individuals who will help break down our conservatism. 	— Shigeru Miyamoto, on designing the Wii controllers \n" + 
    	"Others inspire us, information feeds us, practice improves our performance, but we need quiet time to figure things out, to emerge with new discoveries, to unearth original answers. 	- Dr. Ester Buchholz \n" + 
    	"Politics is the struggle between those who want to make their country the best in the world, and those who believe it already is.  Each group calls the other unpatriotic. \n" + 
    	"We should be taught not to wait for inspiration to start a thing. Action always generates inspiration. Inspiration seldom generates action. 	— Frank Tibolt \n" + 
    	"The world is full of people whose notion of a satisfactory future is, in fact, a return to the idealised past. 	-- Robertson Davies \n" + 
    	"Judge a man by his questions, rather than his answers. 	-- Voltaire \n" + 
    	"Even if you're are on the right track, you'll get run over if you just sit there. 	-- Will Rogers \n" + 
    	"You're not old until regret takes the place of your dreams. \n" + 
    	"\"Christians, like slaves and soldiers, ask no questions.\" 	-- Jerry Falwell (August 11, 1933 - May 15, 2007) \n" + 
    	"\"Ideas are a dime a dozen.  It's execution that counts.\" 	-- Frank Herbert \n" + 
    	"\"There are only two hard things in CS: cache invalidation and naming things.\" 	-- Phil Karlton \n" + 
    	"Free software is part of a broader phenomenon, which is a shift toward recognizing the value of shared work. Historically, shared stuff had a very bad name. The reputation was that people always abused shared things, and in the physical world, something that is shared and abused becomes worthless. In the digital world, I think we have the inverse effect, where something that is shared can become more valuable than something that is closely held, as long as it is both shared and contributed to by everybody who is sharing in it. 	-- Mark Shuttleworth \n" + 
    	"This is an important part of the Internet Dynamic – providing opportunity and not guarantees. 	-- Bob Frankston \n" + 
    	"\"knowledge workers\" are simply those people whose job consists of having interesting conversations. 	-- The Cluetrain Manifesto. Page 123. \n" + 
    	"Find the simplest model that is not a lie is the key to better software design. 	-- David Lorge Parnas (CACM June 2007 - Forum) \n" + 
    	"\"Love\" is that condition in which the happiness of another person is essential to your own. 	Stranger in a Strange Land \n" + 
    	"A programming language that doesn't change the way you think about programming is not worth knowing. 	-- Alan Perlis \n" + 
    	"\"People are much more likely to act their way into a new way of thinking, than think their way into a new way of acting.\" 	-- Richard Pascale \n" + 
    	"\"Don't change the man. Change his environment.\" 	-- Buckminster Fuller \n" + 
    	"Money corrupts the process of reasoning. 	-- Lawrense Lessig \n" + 
    	"Most of us human beings tend to be parochial in our perceptions. What happens is that we have a very limited and narrow view of our self interest and do not adequately weight the price that has to be paid on the other side for any of these measures we are promoting. 	-- Alan Borovoy on CBC's Cross Country Checkup (2008-03-09) \n" + 
    	"If you want to create new markets, or disrupt old ones, you create ubiquitous infrastructure. 	-- Doc Searls \n" + 
    	"Recursion is the root of computation since it trades description for time. \n" + 
    	"The skill of writing is to create a context in which other people can think. 	-- Edwin Schlossbergk \n" + 
    	"See what happens when you put a bunch of guys together that work hard and like what they do. Things get done. 	-- Mike Holmes \n" + 
    	"Life is what happens when you’re busy making plans. 	-- John Lennon \n" + 
    	"The test of a first-rate intelligence is the ability to hold two opposed ideas in the mind at the same time, and still retain the ability to function. 	-- F. Scott Fitzgerald \n" + 
    	"All human beings should try to learn before they die: what they are running from, and to, and why. 	-- James Thurber \n" + 
    	"Free markets are a tool, free speech is a goal. 	-- Tim Bray \n" + 
    	"- Creativity and innovation always builds on the past. - The past always tries to control the creativity that builds upon it. - Free societies enable the future by limiting this power of the past. - Ours is less and less a free society. 	-- Lawrence Lessig \n" + 
    	"You can never entirely stop being what you once were. That's why it's important to be the right person today, and not put it off till tomorrow. 	-- Larry Wall \n" + 
    	"Trust always breaks down first over money. 	-- David Hodskins \n" + 
    	"In battle, exploiting a situation deserves a medal. In business, exploiting a situation deserves condemnation. 	-- Craig Burton \n" + 
    	"To only a fraction of the human race does God give the privilege of earning one’s bread doing what one would have gladly pursued free, for passion. I am very thankful. 	-- The Mythical Man Month \n" + 
    	"Play with boundaries, not within. \n" + 
    	"Conditions are never just right. People who delay action until all factors are favourable do nothing. \n" + 
    	"Success is just like being pregnant: everybody congragulates you, but nobody knows how many times you were fucked... \n" + 
    	"ideas that change business models and who can make money are the most threatening, outside actual religion. 	- John Day (Patterns in Network Architecture) \n" + 
    	"Failure does not bring with it a better chance for future success. It brings a trimming of future ambitions. \n" + 
    	"Everything is vague to a degree you do not realize till you have tried to make it precise. 	-- Bertrand Russell \n" + 
    	"Always be a 1st-rate version of yourself, instead of a 2nd-rate version of somebody else. 	-- Garland \n" + 
    	"I realized the reason I had a lot of stress in the corporate environment is because I didn't fit in that environment, I came to understand that my real passion is getting things done in ways that probably aren't always acceptable in a corporate setting, like saying what's on your mind, actually telling people when you think they're going about something the wrong way, and trying to help them find a better way to do it. 	-- Brett Schklar \n" + 
    	"The way to grow is to do what you love to do. Invest in it. Build it. Be willing to take risks. Keep ethics and the best interests of the people around you in mind. And don't always focus on the money. 	-- Brett Schklar \n" + 
    	"As we benefit from the inventions of others, we should be glad to share our own ... freely and gladly.\" 	-- Franklin \n" + 
    	"\"If you want to succeed, double your failure rate.\" 	-- Thomas Watson. \n" + 
    	"Stop agonizing about what’s not working. Instead, ask yourself, “What’s working well, right now, and how can I do more of it?” 	-- Chip and Dan Heath, What matters now, Pg 50 \n" + 
    	"If you keep thinking about putting on a conference or being a Hollywood screenwriter, and you find the idea terrifies but intrigues you, it’s probably a worthy endeavor for you.  You grow (and thrive!) by doing what excites you and what scares you everyday, not by trying to find your passion. 	-- Derek Sivers, What matters now, Pg 51 \n" + 
    	"Declare war on passivity. Hush the inner voice that insists you’re over the hill, past your prime, unworthy of attaining those dreams. Disbelief is now the enemy, as is the notion of settling. Get hungry — hyena hungry. Get fired up. Find your backbone, and your wings. Flap ‘em. It’s the only way you’ll be able to fly. 	-- J.C. Hutchins, What matters now, Pg 81 \n" + 
    	"Our lives begin to end the day we become silent about things that matter. 	-- Martin Luther King Jr \n" + 
    	"Being negative is easy. There will always be a downside to everything good, a hurdle to everything desirable, a con to every pro. The real courage is in finding the good in what you have, the opportunities in every hurdle, the pros in every con. 	-- Carolyn Hax \n" + 
    	"Until one is committed, there is hesitancy, the chance to draw back-- Concerning all acts of initiative (and creation), there is one elementary truth that ignorance of which kills countless ideas and splendid plans: that the moment one definitely commits oneself, then Providence moves too. All sorts of things occur to help one that would never otherwise have occurred. A whole stream of events issues from the decision, raising in one's favor all manner of unforeseen incidents and meetings and material assistance, which no man could have dreamed would have come his way. Whatever you can do, or dream you can do, begin it. Boldness has genius, power, and magic in it. Begin it now. 	-- Goethe (http://www.goethesociety.org/pages/quotescom.html) \n" + 
    	"Then indecision brings its own delays, And days are lost lamenting over lost days. Are you in earnest? Seize this very minute; What you can do, or dream you can do, begin it; Boldness has genius, power and magic in it. 	-- http://www.goethesociety.org/pages/quotescom.html \n" + 
    	"Not to understand a man's purpose does not make him confused. 	- Master Po - The Vanishing Image \n" + 
    	"To know what you prefer instead of humbly saying Amen to what the world tells you ought to prefer, is to have kept your soul alive.     -- Robert Louis Stevenson \n" + 
    	"When I do good, I feel good. When I do bad, I feel bad. And that's my religion.     -- Abraham Lincoln \n" + 
    	"Take away the ability of an intelligent, principled, hard-working mind to get it wrong, and you take away the whole thing.     -- Kathryn Schulz (http://www.boston.com/bostonglobe/ideas/articles/2010/06/13/the_bright_side_of_wrong/?page=full) \n" + 
    	"Making your mark on the world is hard. If it were easy, everybody would do it. But it's not. It takes patience, it takes commitment, and it comes with plenty of failure along the way. The real test is not whether you avoid this failure, because you won't. It's whether you let it harden or shame you into inaction, or whether you learn from it; whether you choose to persevere.\"     -- President Barack Obama \n" + 
    	"Ignorance: the root of all evil.     -- Plato \n" + 
    	"Be kind, for everyone you meet is fighting a hard battle.     -- Plato \n" + 
    	"The price good men pay for indifference to publiuc affairs is to be ruled by evil men.     -- Plato \n" + 
    	"When a respected information source covers something where you have on-the-ground experience, the result is often to make you wonder how much fecal matter you’ve swallowed in areas outside your own expertise.     -- Rusty Russell \n" + 
    	"Any time someone puts a lock on something that belongs to you, and won't give you a key, they're not doing it for your benefit.     -- Doctorow's First Law \n" + 
    	"Show initiative, for fortune favors the bold. ... Explore, challenge assumptions, and don't let lots of prior art discourage you.     -- David A. Patterson (Communications of the ACM, March 2009, Pg 30) \n" + 
    	"Architecture trumps licensing any time.     -- Tim O'Reilly \n" + 
    	"When the complexity of the problems humans must solve exceeds the capability of our brains, we turn to beliefs.     -- Rebecca Costa     (http://www.thedailybeast.com/blogs-and-stories/2010-10-13/global-warming-denial-explained-by-rebecca-costa-/) \n" + 
    	"I tell people if it’s in the news don’t worry about it. Because by definition news is something that almost never happens.     -- Bruce Schneier (http://www.youtube.com/watch?feature=player_embedded&v=CGd_M_CpeDI) \n" + 
    	"When you have lots of ideas it's like: how can I get people to steal this idea and do it? But when you have very few...     -- Jay Rosen (@jayrosen_nyu) \n" + 
    	"Luck is probability taken personally. \n" + 
    	"The signature of mediocrity is not an unwillingness to change. The signature of mediocrity is inconsistency.     -- Jim Collins \n" + 
    	"Pure academic research and applied industrial research are complementary, and should be pursued concurrently and in collaboration. The goal of industrial research is (and should always be) to pluck the 'low-hanging fruit'; that is, to solve the easiest parts of the most prevalent problems, in the particular circumstances of here and now. But the goal of the pure research scientist is exactly the opposite: it is to construct the most general theories, covering the widest possible range of phenomena, and to seek certainty of knowledge that will endure for future generations. It is to avoid the compromises so essential to engineering, and to seek ideals like accuracy of measurement, purity of materials, and correctness of programs, far beyond the current perceived needs of industry or popularity in the market-place. For this reason, it is only scientific research that can prepare mankind for the unknown unknowns of the forever uncertain future.     -- C.A.R. Hoare     (http://cacm.acm.org/magazines/2009/10/42360-retrospective-an-axiomatic-basis-for-computer-programming/fulltext) \n" + 
    	"Secrecy can spring from the best motives; but as it grows it begins to exist only for itself, only for its own sake, only to cover its own abuses.     -- Senator Chris Dodd \n" + 
    	"\"Fanaticism consists in redoubling your efforts when you have forgotten your aim.\"     -- George Santayana \n" + 
    	"Builders need to take their preeminent position back from the traders for the economy of the future to flourish.     -- Richard Florida (The Great Reset) \n" + 
    	"Not every venture is about capital.     -- Firefox billboard (http://www.flickr.com/photos/fligtar/5569324234/) \n" + 
    	"A good plan violently executed now is better than a perfect plan executed next week.         -- George Patton \n" + 
    	"Never ignore a gut feeling but never assume it's enough.         -- Robert Heller \n" + 
    	"Riches do not exhilerate us so much with their possesion as they torment us with thier loss.         -- Epicurus \n" + 
    	"Get a good idea, and stay with it. Dog it, and work at it until it's done, and done right.         -- Walt Disney \n" + 
    	"It is easier to move a problem around (for example, by moving the problem to a different part of the overall network architecture) than it is to solve it.         -- RFC 1925 \n" + 
    	"The significant problems we face cannot be solved at the same level of thinking we were at when we created them.         -- Einstein \n" + 
    	"Programming is the art of telling another human being what one wants the computer to do.         -- Donald Knuth \n" + 
    	"There are decades when nothing happens... And there are weeks when decades happen.         -- Vladimir Ilyich Lenin \n" + 
    	"When you adopt a tool you adopt the management philosophy embedded in that tool.         -- Clay Shirky (Ted Talk - How the Internet will (one day) transform government) \n" + 
    	"The ability to master complexity is not the same as the ability to extract simplicity. " ;
    
    private static String[] quotes;
    
    static {
	quotes = quotesStr.split("\n");
	
	
    }
    
    public static List<String> getRandom(int count) {
	List<String> list = new ArrayList<>(count);
	for(int i=0;i< count;i++) {
	    list.add(getRandomQuote());
	}
	return list;
    }
    
    public static String getRandomQuote() {
	int i = (int)Math.round(Math.random()*(quotes.length-1));
	return quotes[i];
    }
    
    public static void main(String[] args) {
	System.out.println(getRandomQuote());
    }

}
