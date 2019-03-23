#include <SoftwareSerial.h>

SoftwareSerial BT(10, 11);

const int flex_thumb = A0;
const int flex_index = A1;
const int flex_middle = A2;
const int flex_ring = A3;
const int flex_little = A4;

bool thumb = false;
bool index = false;
bool middle = false;
bool ring = false;
bool little = false;

bool new_sign = false;

const float VCC = 4.98;
const float R_DIV = 3000;


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  BT.begin(9600);
  pinMode(flex_thumb, INPUT);
  pinMode(flex_index, INPUT);
  pinMode(flex_middle, INPUT);
  pinMode(flex_ring, INPUT);
  pinMode(flex_little, INPUT);
  new_sign = true;
  
  
}

void loop() {
  // put your main code here, to run repeatedly:

  int flexADC_thumb = analogRead(flex_thumb);
  int flexADC_index = analogRead(flex_index);
  int flexADC_middle = analogRead(flex_middle);
  int flexADC_ring = analogRead(flex_ring);
  int flexADC_little = analogRead(flex_little);
  
  if (flexADC_thumb >= 935.0)
  {
    thumb = true;
  }
  if (flexADC_index >= 879.0)
  {
    index = true;
  }
  if (flexADC_middle >= 807.0)
  {
    middle = true;
  }
  if (flexADC_ring >= 280.0)
  {
    ring = true;
  }
  if (flexADC_little >= 193.0)
  {
    little = true;
  }

  if (new_sign)
  {
    if (thumb && !index && !middle && ring && little)
    {
      Serial.println("My");
      BT.print("1"); // code for 'my'
      new_sign = false;
    }
    
    if (!thumb && index && middle && ring && !little)
    {
      Serial.println("name");
      BT.print("2"); // code for 'name'
      new_sign = false;
    }
    
    if (!thumb && !index && !middle && ring && little)
    {
      Serial.println("is");
      BT.print("3"); // code for 'is'
      new_sign = false;
    }
    if (thumb && !index && !middle && !ring && !little)
    {
      Serial.println("am");
      BT.print("4"); // code for 'am'
      new_sign = false;
    }
    if (!thumb && index && middle && ring && little)
    {
      Serial.println("good");
      BT.print("5"); // code for 'good'
      new_sign = false;
    }
    if (thumb && !index && middle && !ring && !little)
    {
      Serial.println("hi");
      BT.print("6"); // code for 'hi'
      new_sign = false;
    }
    if (thumb && index && middle && ring && !little)
    {
      Serial.println("how");
      BT.print("7"); // code for 'how'
      new_sign = false;
    }
    if (thumb && index && middle && ring && little)
    {
      Serial.println("are");
      BT.print("8"); // code for 'are'
      new_sign = false;
    }
    if (thumb && !index && middle && ring && little)
    {
      Serial.println("you");
      BT.print("9"); // code for 'you'
      new_sign = false;
    }
    if (thumb && index && middle && !ring && !little)
    {
      Serial.println("I");
      BT.print("a"); // code for 'I'
      new_sign = false;
    }
    if (!thumb && !index && middle && ring && !little)
    {
      Serial.println("love");
      BT.print("b"); // code for 'love'
      new_sign = false;
    }
    if (thumb && index && !middle && !ring && little)
    {
      Serial.println("sleep");
      BT.print("c"); // code for 'sleep'
      new_sign = false;
    }
    if (thumb && index && !middle && !ring && !little)
    {
      Serial.println("hate");
      BT.print("d"); // code for 'hate'
      new_sign = false;
    }
    if (thumb && !index && middle && ring && !little)
    {
      Serial.println("me");
      BT.print("e"); // code for 'me'
      new_sign = false;
    }
    if (thumb && !index && !middle && ring && !little)
    {
      Serial.println("friend");
      BT.print("f"); // code for 'me'
      new_sign = false;
    }
    if (thumb && index && !middle && !ring && !little)
    {
      Serial.println("friend");
      BT.print("f"); // code for 'me'
      new_sign = false;
    }

    Serial.println("new word");
    
  }

  else 
  {
    if (!thumb && !index && !middle && !ring && !little)
    {
      new_sign = true;
    }
  }

  Serial.println(String(flexADC_thumb) + ", " + String(flexADC_index) + ", " + String(flexADC_middle) + ", " + String(flexADC_ring) + ", " + String(flexADC_little));
  thumb = false;
  index = false;
  middle = false;
  ring = false;
  little = false;

  delay(750);
  
}
