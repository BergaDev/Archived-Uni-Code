%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 function PART3(action);                                               
% Part 1 ofECTE196 Lab experiment on Signals                                                                      
%       possible actions:                                                   
%         'start'                                                           
%         'redraw'                                                          
%         'done'                                                            
%         'setfreq'
%		 'setamp
	%		'setnoise'                                                         
                                                                      
if nargin<1,                                                          
   action='start';                                                    
                                                                      
end;                                                                  
                                                                      
global PART3_DAT                                                       
                                                                      
if strcmp(action,'start'),                                            
                                                                      
    %====================================                             
    % Graphics initialization                                         
    oldFigNumber = watchon;                                           
    figNumber = figure;                                               
    set(gcf, ...                                                      
        'NumberTitle','off', ...                                      
        'Name','Part 3 - Noise', ...                      
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
                                                                      
popup=uicontrol('Style','Popup','String','square|square+noise', ...                                                                  
        'Position', btnPos, ...                                       
        'Units','normalized',...                                      
        'CallBack','PART3(''redraw'')');                               
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
        'String','Noise Amplitude (V)');                                      
                                                                      
    btnPos=[left  yLabelPos-labelHt-btnHt-btnOffset ...               
            0.5*btnWid+frmBorder+0.07  btnHt];                        
    amp_field = uicontrol( ...                                      
        'Style','edit', ...                                           
        'Position', btnPos, ...                                       
        'Units','normalized', ...                                     
        'String',' 0.1',...                                             
        'CallBack','PART3(''setamp'')');                             
 
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
        'CallBack','PART3(''setfreqf'')');                             
                                                                      
 %====================================                                
    % The INFO button                                                 
    uicontrol( ...                                                    
        'Style','push', ...                                           
        'Units','normalized', ...                                     
        'Position',[left bottom+(2*labelHt)+spacing btnWid 2*labelHt], ....                                                                   
        'String','Info', ...                                          
        'Callback','PART3(''info'')');                                 

   %========================================                          
   % The CLOSE button                                                 
    done_button=uicontrol('Style','Pushbutton', ...                   
        'Position',[left bottom btnWid 2*labelHt], ...                
        'Units','normalized','Callback', ...                          
        'PART3(''done'')','String','Close');                           
%====================================                              
                                                                      
%=======================================================              
% Create intial signal                                               
amp=0.1;
k=20;                                                          
fsample=20;                                                           
freq=5;    		% hertz                                                    
N=400*k;    % number of samples                                      
                                                                      
t0=0;       % seconds                                               
t1=1;      % seconds                                              
t=linspace(t0,t1,N)';                                             
T=(t1-t0)/N;            % sampling rate in seconds               
 
noise = amp*sin(50*t*2*pi);                                                                     
                                                                      
% time domain                                                    
val = get(popup,'Value');                                         
if (val == 1), 	% Square wave only                                                   
        tt=freq*t*2*pi;                                               
        tmp=rem(tt,2*pi);                                             
        f= 5*(2*rem((tt<0)+(tmp>pi | tmp<-pi)+1,2)-1);                                               
elseif (val == 2),                % square wave + noise                                
        tt=freq*t*2*pi;                                               
        tmp=rem(tt,2*pi);                                             
        f= noise + 5*(2*rem((tt<0)+(tmp>pi | tmp<-pi)+1,2)-1);                                               
end;                                                              
                                                                      
                                                                      
ax_ana=axes('Position',[.10 .10 .6 .8]);
analog=plot(t,f,'EraseMode','xor');                               
axis([t0 t1 -1*10-.25*10 1*10+0.25*10]);                          
grid;                                                                
ylabel('Volts');                                                       
xlabel('Time (Seconds)');                                             
title('Square Wave + Noise');                                               
                                                                      
PART3_DAT=[fsample; freq; amp; popup; analog; N; freqf_field; amp_field; k; t(:); ];                                 
                                                                      
watchoff(oldFigNumber);                                               
                                                                      
%===========================================================          
elseif strcmp(action,'redraw'),                                       
                                                                      
    fsample=PART3_DAT(1);                                             
    freq=PART3_DAT(2);                                                
    amp=PART3_DAT(3);                                                 
    popup=PART3_DAT(4);                                               
    analog=PART3_DAT(5);                                              
    N=PART3_DAT(6);                                                   
    freqf_field=PART3_DAT(7);                                         
    amp_field=PART3_DAT(8);                                         
    k=PART3_DAT(9);                                                  
    t=PART3_DAT(10:10+N-1);                                           
%	t0=0;       % seconds                                               
%	t1=1;      % seconds                                              
%	t2=linspace(t0,t1,N)';                                             
	
	noise = amp*sin(50*t*2*pi);
	val = get(popup,'Value');         	
	if (val == 1),                                                    
        tt=freq*t*2*pi;                                               
        tmp=rem(tt,2*pi);                                             
        f= 5*(2*rem((tt<0)+(tmp>pi | tmp<-pi)+1,2)-1);               
	elseif (val == 2),                                                    
        tt=freq*t*2*pi;                                               
        tmp=rem(tt,2*pi);                                             
        f= noise + 5*(2*rem((tt<0)+(tmp>pi | tmp<-pi)+1,2)-1);               
		end;                                                                  

set(analog,'YData',f);                                            
drawnow;                                                           

%======================================                               
elseif strcmp(action,'setfreqf'),                                     
                                                                      
    freqf_field=PART3_DAT(7);                                          
    xf = str2num(get(freqf_field,'string'));                          
                                                                      
if isempty(xf),   % handle the non-numeric case                       
        set(PART3_DAT(7),'string',num2str(PART3_DAT(2)));               
    elseif (xf>60),                                                   
        set(PART3_DAT(7),'string',num2str(60));                        
        xf=60;                                                        
        PART3_DAT(2)=xf;                                               
    else                                                              
    PART3_DAT(2)=xf;                                                   
    end;                                                              
                                                                      
    PART3('redraw');                                                   
                                                                      
%============================================================         
elseif strcmp(action,'setamp'),                                     
                                                                      
    amp_field=PART3_DAT(8);                                          
    xf = str2num(get(amp_field,'String'));                          
                                                                      
if isempty(xf),   % handle the non-numeric case                       
        set(PART3_DAT(8),'string',num2str(PART3_DAT(3)));               
    elseif (xf>10),                                                   
        set(PART3_DAT(8),'string',num2str(10));                        
        xf=10;                                                        
        PART3_DAT(3)=xf;                                               
    else                                                              
    PART3_DAT(3)=xf;                                                   
    end;                                                              
                                                                      
    PART3('redraw');                                                   
%======================================                               
elseif strcmp(action,'done'),                                         
    % close the figure window that is showing the window fnction:     
    % u = get(gcf,'userdata');                                        
%    if (PART3_DAT(8)~=-1),                                            
%        close(PART3_DAT(8));                                          
%    end;                                                              
    close(gcf);                                                       
    clear global PART3_DAT                                             
    clear global ADDIT_DAT                                            
                                                                      
                                                                      
%====================================                                 
elseif strcmp(action,'info'),                                         
    ttlStr = 'Noise';                            
                                                                      
    hlpStr1= ...                                                      
        ['This illustrates the presence of 50Hz noise on a  '
		 'square wave signal                                '
		 '                                                  '
		 'The square wave represents a digital signal, which'
		 'is transmitted along a line (ethernet for example)'
		 'Along the way, the signal picks up noise, which   '
		 'can introduce the possibility of error in the     '
		 'signal                                            '];        
                                                                     
        helpfun(ttlStr, hlpStr1);                            
end                                                                   
