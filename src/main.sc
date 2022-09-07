require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: words.csv
  name = word
  var = word
require: functions.js  
  
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
            $session.keys = Object.keys(word);
            $session.word = word[chooseRandWordKey($session.keys)].value.word;
            $reactions.answer($session.word);
        a: Отлично, начнем! У тебя есть 6 жизней: стоимость одной жизни - неправильная буква или слово. Слово можно угадывать по буквам или целиком.
        a: Я загадал слово {{hiddenWordOutput($session.word)}}.
        
    state: PlayHangerman
        state: LetterResieved
            intent: /Letter
            # if(checkLetter = true

    state: Bye
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}
