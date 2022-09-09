require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: words.csv
  name = words
  var = words
require: functions.js 
require: patterns.sc 
    
#$alphabet = $entity<$Alphabet> || converter = AlphabetTagConverter    
    
  
theme: /

    state: Start || modal = true
        q!: $regex</start>
        intent!: /LetsPlay
        script:
            $session = {}
            $client = {}
            $temp = {}
            $response = {}
        a: Привет! Предлагаю сыграть в игру "Виселица". Я загадываю слово, а ты пытаешься по буквам его отгадать. Готов?
        
    state: Rules
        intent!: /ReadyToPlay
        script:
            $session.keys = Object.keys(words);
            $session.word = words[chooseRandWordKey($session.keys)].value.name;
            $reactions.answer($session.word);
        if: !$client.wasAuthorised
            a: Отлично, начнем! У тебя есть 6 жизней: стоимость одной жизни - неправильная буква или слово. Слово можно угадывать по буквам или целиком.
            a: Я загадал слово {{hiddenWordOutput($session.word)}}.
        else:
            a: Я загадал слово {{hiddenWordOutput($session.word)}}.
        
    state: PlayHangerman
        state: LetterResieved
            q: *($let * $alphabet)*
            a: Вы назвали букву {{$parseTree.alphabet.text)}}
            # script:
            # if(checkLetter = true) {
                # ...
            #endGame
            #remainLetters = $session.word.length
            #if remainLetters < 0 {
                #a:
            

    state: Bye
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}
        go:..
