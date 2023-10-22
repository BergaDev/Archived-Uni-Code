%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 function PART2(action);                                               
% Part 1 ofECTE196 Lab experiment on Signals                                                                      
%       possible actions:                                                   
%         'start'                                                           
%         'redraw'                                                          
%         'done'                                                            
%         'setfreq'
%		 'setamp'                                                         
                                                                      
if nargin<1,                                                          
   action='start';                                                    
                                                                      
end;                                                                  
                                                                      
global PART2_DAT                                                       
                                                                      
if strcmp(action,'start'),                                            
                                                                      
    %====================================                             
    % Graphics initialization                                         
    oldFigNumber = watchon;                                           
    figNumber = figure;                                               
    set(gcf, ...                                                      
        'NumberTitle','off', ...                                      
        'Name','Part 2 - Supersosition of Signals', ...                      
        'backingstore','off',...                                      
        'Units','normalized');                                        
 
	   % Information for all buttons                                     
    labelColor=192/255*[1 1 1];                                       
    top=0.95;                                                         
    bottom=0.05;                                                      
    left=0.75;                                                        
    yInitLabelPos=0.90;                                               
    left = 0.78;                                                      
    labelWid=0.18;                                                    
    labelHt=0.05;                                                     
    btnWid = 0.18;                                                    
    btnHt=0.07;                                                       
    % Spacing between the label and the button for the same command   
    btnOffset=0.003;                                                  
    % Spacing between the button and the next command's label         
    spacing=0.05;                                                     
 %====================================                                
    % The CONSOLE frame                                               
    frmBorder=0.02;                                                   
    yPos=0.05-frmBorder;                                              
    frmPos=[left-frmBorder yPos btnWid+2*frmBorder 0.9+2*frmBorder];  
    h=uicontrol( ...                                                  
        'Style','frame', ...                                          
        'Units','normalized', ...                                     
        'Position',frmPos, ...                                        
        'BackgroundColor',[0.5 0.5 0.5]);                             
                                                                      
                                                                      
    %====================================                             
    % The SIGNAL command popup button                                 
                                                                      
    btnNumber=1;                                                      
    yLabelPos=top-(btnNumber-1)*(btnHt+labelHt+spacing);              
    labelStr=' Signal 2';                                               
                                                                      
    % Generic label information                                       
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    uicontrol( ...                                                    
        'Style','text', ...                                           
        'Units','normalized', ...                                     
        'Position',labelPos, ...                                      
        'BackgroundColor',labelColor, ...                             
        'HorizontalAlignment','left', ...                             
        'String',labelStr);                                           
                                                                      
    btnPos=[left yLabelPos-labelHt-btnHt-btnOffset btnWid btnHt];     
                                                                      
popup=uicontrol('Style','Popup','String','sine|cosine', ...                                                                  
        'Position', btnPos, ...                                       
        'Units','normalized',...                                      
        'CallBack','PART2(''redraw'')');                               
  %====================================                               
  %====================================                               
    % The Amplitude editable text box                                  
    btnNumber=4;                                                      
    yLabelPos=top-(btnNumber-1)*(btnHt+labelHt+spacing);              
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    amp_text = uicontrol( ...                                        
        'Style','text', ...                                           
        'Position', labelPos, ...                                     
        'Units','normalized', ...                                     
        'BackgroundColor',[0.5 0.5 0.5], ...                          
        'ForegroundColor','white', ...                                
        'String','Amplitude (V)');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    amp_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 10',...                                             
        'CallBack','PART2(''setamp'')');                             
 %====================================                                
    % The FUNDAMENTAL editable text box                               
    btnNumber=3;                                                      
    yLabelPos=top-(btnNumber-1)*(btnHt+labelHt+spacing)+0.15;         
    labelPos=[left yLabelPos-labelHt labelWid labelHt];               
    freq_text = uicontrol( ...                                        
        'Style','text', ...                                           
        'Position', labelPos, ...                                     
        'Units','normalized', ...                                     
        'BackgroundColor',[0.5 0.5 0.5], ...                          
        'ForegroundColor','white', ...                                
        'String','Fundamental');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    freqf_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 5',...                                             
        'CallBack','PART2(''setfreqf'')');                             
                                                                      
 %====================================                                
    % The INFO button                                                 
    uicontrol( ...                                                    
        'Style','push', ...                                           
        'Units','normalized', ...                                     
        'Position',[left bottom+(2*labelHt)+spacing btnWid 2*labelHt], ....                                                                   
        'String','Info', ...                                          
        'Callback','PART2(''info'')');                                 
                                                                      
   %========================================                          
   % The CLOSE button                                                 
    done_button=uicontrol('Style','Pushbutton', ...                   
        'Position',[left bottom btnWid 2*labelHt], ...                
        'Units','normalized','Callback', ...                          
        'PART2(''done'')','String','Close');                           
%====================================                              
                                                                      
%=======================================================              
% Create intial signal                                               
amp=10;
k=20;                                                          
fsample=20;                                                           
freq=5;    		% hertz                                                    
N=400*k;    % number of samples                                      
                                                                      
t0=0;       % seconds                                               
t1=1;      % seconds                                              
t=linspace(t0,t1,N)';                                             
T=(t1-t0)/N;            % sampling rate in seconds               
 
sig_1 = 5*sin(10*t*2*pi);                                                                     
                                                                      
% time domain                                                    
val = get(popup,'Value');                                         
if (val == 1),                                                    
        f = sig_1 +amp*sin(freq*t*2*pi);  			% sinusoidal wave                             
elseif (val == 2),                                                
        f = sig_1 + amp*cos(freq*t*2*pi);                                       
elseif (val == 3),   								% square wave                                
        tt=freq*t*2*pi;                                               
        tmp=rem(tt,2*pi);                                             
        f=0.1*sig_1 + amp*(2*rem((tt<0)+(tmp>pi | tmp<-pi)+1,2)-1);               
end;                                                              
                                                                      
                                                                      
ax_ana=axes('Position',[.10 .10 .6 .8]);
analog=plot(t,f,'EraseMode','xor');                               
axis([t0 t1 -1*amp-.25*amp 1*amp+0.25*amp]);                          
grid;                                                                
ylabel('Volts');                                                       
xlabel('Time (Seconds)');                                             
title('10Hz 5V Sinusoid + Signal 2');                                               
                                                                      
PART2_DAT=[fsample; freq; amp; popup; analog; N; freqf_field; amp_field; k; t(:); ];                                 
                                                                      
watchoff(oldFigNumber);                                               
                                                                      
%===========================================================          
elseif strcmp(action,'redraw'),                                       
                                                                      
    fsample=PART2_DAT(1);                                             
    freq=PART2_DAT(2);                                                
    amp=PART2_DAT(3);                                                 
    popup=PART2_DAT(4);                                               
    analog=PART2_DAT(5);                                              
    N=PART2_DAT(6);                                                   
    freqf_field=PART2_DAT(7);                                         
    amp_field=PART2_DAT(8);                                         
    k=PART2_DAT(9);                                                  
    t=PART2_DAT(10:10+N-1);                                           
%	t0=0;       % seconds                                               
%	t1=1;      % seconds                                              
%	t2=linspace(t0,t1,N)';                                             
	
	sig_1 = 5*sin(10*t*2*pi);
	
	val = get(popup,'Value');                                             
	if (val == 1),                                                    
        f = sig_1  + amp*sin(freq*t*2*pi);                                       
    elseif (val == 2),                                                
        f = sig_1 + amp*cos(freq*t*2*pi);                                       
    elseif (val == 3),   % square wave                                
        tt=freq*t*2*pi;                                               
        tmp=rem(tt,2*pi);                                             
        f= sig_1 + amp*(2*rem((tt<0)+(tmp>pi | tmp<-pi)+1,2)-1);               
    end;                                                                  

set(analog,'YData',f);                                            
drawnow;                                                           

%======================================                               
elseif strcmp(action,'setfreqf'),                                     
                                                                      
    freqf_field=PART2_DAT(7);                                          
    xf = str2num(get(freqf_field,'string'));                          
                                                                      
if isempty(xf),   % handle the non-numeric case                       
        set(PART2_DAT(7),'string',num2str(PART2_DAT(2)));               
    elseif (xf>60),                                                   
        set(PART2_DAT(7),'string',num2str(60));                        
        xf=60;                                                        
        PART2_DAT(2)=xf;                                               
    else                                                              
    PART2_DAT(2)=xf;                                                   
    end;                                                              
                                                                      
    PART2('redraw');                                                   
                                                                      
%============================================================         
elseif strcmp(action,'setamp'),                                     
                                                                      
    amp_field=PART2_DAT(8);                                          
    xf = str2num(get(amp_field,'String'));                          
                                                                      
if isempty(xf),   % handle the non-numeric case                       
        set(PART2_DAT(8),'string',num2str(PART2_DAT(3)));               
    elseif (xf>10),                                                   
        set(PART2_DAT(8),'string',num2str(10));                        
        xf=10;                                                        
        PART2_DAT(3)=xf;                                               
    else                                                              
    PART2_DAT(3)=xf;                                                   
    end;                                                              
                                                                      
    PART2('redraw');                                                   
                                                                      

%======================================                               
elseif strcmp(action,'done'),                                         
    % close the figure window that is showing the window fnction:     
    % u = get(gcf,'userdata');                                        
%    if (PART2_DAT(8)~=-1),                                            
%        close(PART2_DAT(8));                                          
%    end;                                                              
    close(gcf);                                                       
    clear global PART2_DAT                                             
    clear global ADDIT_DAT                                            
                                                                      
                                                                      
%====================================                                 
elseif strcmp(action,'info'),                                         
    ttlStr = 'Analog vs. Digital Signals';                            
                                                                      
    hlpStr1=...                                                      
        ['Superposition of signals simply corresponds to   '
	     'adding two signals together. This exercise       '
		 'illustrates this.                                '
		 '                                                 '
		 'The fundamental and amplitude box changes the    '
		 'frequency of signal 2, which is added to a       '
		 '10Hz, 5V sinusoidal signal. Thus a frequency     '
		 'or amplitude of zero will just show this 10Hz    '
		 'signal                                           '];            
                                                                      
    hlpStr2= ...                                                      
        ['Any general signal can be formed using a number  '
	     'of sinusoidal signals of different amplitude     '
		 'and frequency. This is known as a fourier series '];        
                                                                      
                                                                      
        helpfun(ttlStr, hlpStr1, hlpStr2);                            
end                                                                   
